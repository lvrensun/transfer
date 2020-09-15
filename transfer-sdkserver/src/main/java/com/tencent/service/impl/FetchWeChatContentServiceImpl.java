package com.tencent.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.http.Response;
import com.tencent.config.QiNiuCloudConfig;
import com.tencent.constants.MsgType;
import com.tencent.model.*;
import com.tencent.service.FetchWeChatContentService;
import com.tencent.utils.GListUtil;
import com.tencent.utils.RSAUtil;
import com.tencent.utils.Upload;
import com.tencent.wework.Finance;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class FetchWeChatContentServiceImpl extends BaseService implements FetchWeChatContentService {
    @Resource
    private QiNiuCloudConfig qiNiu;
    @Value("${qiniuyun.callback.url}")
    private String callback_url;
    @Resource
    private Upload uploadData;
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 获聊天记录信息
     * @param param
     * @return map
     * @author Lvshiyang
     */
    @Override
    public Map<String, Object> getChatData(ParamChatDataModel param) {
        long s1 = System.currentTimeMillis();
        /**
         * 初始化SDK
         */
        long sdk = 0;
        int init = 0;
        try {
            sdk = Finance.NewSdk();
            log.warn("sdk:{}", sdk);
            init = Finance.Init(sdk, param.getCorpid(), param.getCorpsecret());
            log.warn("init:{}", init);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (init != 0) {
            return getFailRtn("SDK初始化失败！");
        }
        long s2 = System.currentTimeMillis();
        log.info("初始化SDK消耗 {} 毫秒", s2 - s1);
        /**
         * 拉取聊天记录函数
         * Return值=0表示该API调用成功
         * @param [入]  sdk          NewSdk返回的sdk指针
         * @param [入]  seq          从指定的seq开始拉取消息，注意的是返回的消息从seq+1开始返回，seq为之前接口返回的最大seq值。首次使用请使用seq:0
         * @param [入]  limit        一次拉取的消息条数，最大值1000条，超过1000条会返回错误
         * @param [入]  proxy        使用代理的请求，需要传入代理的链接。如：socks5://10.0.0.1:8081 或者 http://10.0.0.1:8081
         * @param [入]  passwd       代理账号密码，需要传入代理的账号密码。如 user_name:passwd_123
         * @param [入]  timeout      超时时间，单位秒
         * @param [出] chatDatas    返回本次拉取消息的数据，slice结构体.内容包括errcode/errmsg，以及每条消息内容。示例如下：
         */
        long slice = Finance.NewSlice();
        long ret = Finance.GetChatData(sdk, param.getSeq(), param.getLimit(), param.getProxy(), param.getPaswd(), param.getTimeout(), slice);
        log.info("ret:{}", ret);
        if (ret != 0) {
            return getFailRtn("拉取聊天记录失败！");
        }
        String talkRecord = Finance.GetContentFromSlice(slice);
        long s4 = System.currentTimeMillis();
        log.info("获取内容调用 GetChatData方法消耗： {} 毫秒", s4 - s2);

        ChatDatasModel chatDataModel = JSONObject.parseObject(talkRecord, ChatDatasModel.class);
        log.info("getchatdata 聊天记录:{} ", chatDataModel);
        /**
         * 解密 encrypt_random_key,encrypt_chat_msg
         */
        List<ChatDatas> chatList = chatDataModel.getChatdata();
        List<List<ChatDatas>> cList = GListUtil.averageAssign(chatList, 8);
        CountDownLatch latch = new CountDownLatch(8);
        long s6 = System.currentTimeMillis();

        List<ResultModel> resultModelsList = Collections.synchronizedList(new ArrayList<ResultModel>());
        long finalSdk1 = sdk;
        AtomicInteger cv = new AtomicInteger(1);
        cList.forEach(cst -> {
            log.info("****************  总数据量:{}  解密第 ("+cv+") 批数据，数量为: {}", chatList.size(), cst.size() + "  ****************");
            threadPoolTaskExecutor.execute(() -> {
                log.info("当前线程名称:  ------ {} ------  ",Thread.currentThread().getName());
                for (ChatDatas chat : cst) {
                    long finalSdk = finalSdk1;
                    long msg = Finance.NewSlice();
                    try {
                        //use prikey to decrpyt get encryptKey
                        String encryptKey = null;
                        try {
                            encryptKey = RSAUtil.decryptByPriKey(chat.getEncrypt_random_key(), param.getPriKey());
                        } catch (Exception e) {
                            continue;//兼容处理，防止私钥变更。
                        }
                        long stat = Finance.DecryptData(finalSdk, encryptKey, chat.getEncrypt_chat_msg(), msg);
                        if (stat != 0) {
                            log.warn("ChatData:{}解码出现问题：", chat);
                            return;
                        }
                        System.out.println("decrypt ret:" + stat + " msg:" + Finance.GetContentFromSlice(msg));
                        //获取解密后数据信息
                        String resultCont = Finance.GetContentFromSlice(msg);
                        Map map = JSONObject.parseObject(resultCont, Map.class);
                        ResultModel resultModel = new ResultModel();
                        String msgtype = null;
                        try {
                            msgtype = (String) map.get("msgtype");
                        } catch (NullPointerException n) {
                            resultModel.setMsgtype(MsgType.CHANGE_ENTERPRISE_LOG);
                        }
                        resultModel.setSeq(String.valueOf(chat.getSeq()));
                        resultModel.setMsgid(map.get("msgid").toString());
                        resultModel.setPublickey_ver(chat.getPublickey_ver());
                        resultModel.setEncrypt_random_key(encryptKey);
                        resultModel.setEncrypt_chat_msg(chat.getEncrypt_chat_msg());
                        resultModel.setMsgtype(msgtype);
                        resultModel.setContent(StringEscapeUtils.unescapeJava(resultCont));
                        resultModelsList.add(resultModel);
                        log.info("数据： " +resultModelsList);
                    } catch (Exception e) {
                        log.warn("解码失败");
                        e.printStackTrace();
                    }finally {
                        Finance.FreeSlice(msg);
                    }
                }
                latch.countDown();
            });
            log.info("****************  打开 ({}) 号栅栏  ****************",cv);
            cv.incrementAndGet();
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            log.error("阻塞线程异常", e);
            e.printStackTrace();
        }

        long s8 = System.currentTimeMillis();
        log.info("解密内容调用 DecryptData方法解码消耗： {} 毫秒", s8 - s6);
        Finance.DestroySdk(sdk);
        return getSuccessRtn(resultModelsList);
    }

    /**
     * 获取媒体文件
     * @param param
     * @return map
     * @author Lvshiyang
     */
    @Override
    public Map<String, Object> getMediaData(ParamMediaDataModel param) {
        long stt = System.currentTimeMillis();
        /**
         * 初始化SDK
         */
        long sdk = Finance.NewSdk();
        int init = Finance.Init(sdk, param.getCorpid(), param.getCorpsecret());
        if (init != 0) {
            return getFailRtn("SDK初始化失败！");
        }

        param.getSdkFiledDetailModelList().stream().forEach(sdkFiledDetailModel ->
                threadPoolTaskExecutor.execute(() -> {
                    {
                        //分片次数
                        int count = 0;
                        int byteslen = 0;
                        String indexbuf = "";
                        long startTime = 0;
                        int statCode = 0;
                        String statMsg = null;
                        //消息分片的字节数组集合
                        List<byte[]> bytesList = new ArrayList();
                        while (true) {
                            long media_data = Finance.NewMediaData();
                            /**
                             * 拉取媒体消息函数 : Finance.GetMediaData
                             * Return值=0表示该API调用成功
                             * @param [in]  sdk				NewSdk返回的sdk指针
                             * @param [in]  sdkFileid		从GetChatData返回的聊天消息中，媒体消息包括的sdkfileid
                             * @param [in]  proxy			使用代理的请求，需要传入代理的链接。如：socks5://10.0.0.1:8081 或者 http://10.0.0.1:8081
                             * @param [in]  passwd			代理账号密码，需要传入代理的账号密码。如 user_name:passwd_123
                             * @param [in]  indexbuf		媒体消息分片拉取，需要填入每次拉取的索引信息。首次不需要填写，默认拉取512k，后续每次调用只需要将上次调用返回的outindexbuf填入即可。
                             * @param [out] media_data		返回本次拉取的媒体数据.MediaData结构体.内容包括data(数据内容)/outindexbuf(下次索引)/is_finish(拉取完成标记)
                             * @return 返回是否调用成功
                             * 0   - 成功  !=0 - 失败
                             */
                            if (startTime == 0) {
                                startTime = System.currentTimeMillis();
                                log.info("({})拉取分片开始..........", sdkFiledDetailModel.getMsgid());
                            }
                            long ret = Finance.GetMediaData(sdk, indexbuf, sdkFiledDetailModel.getSdkfiled(), param.getProxy(), param.getPasswd(), Integer.parseInt(param.getTimeout()), media_data);
                            byte[] bytes = Finance.GetData(media_data);
                            byteslen += bytes.length;
                            Collections.addAll(bytesList, bytes);
                            //拉取次数计数
                            count++;
                            if (ret != 0) {
                                statCode = 1;
                                callback(null,ret,statCode, null);
                                return;
                            }
                            System.out.printf("getmediadata outindex len:%d, data_len:%d, is_finis:%d\n", Finance.GetIndexLen(media_data), Finance.GetDataLen(media_data), Finance.IsMediaDataFinish(media_data));
                            //Finance.IsMediaDataFinish(media_data) == 1)时拉取完成，上传千牛云。
                            if (Finance.IsMediaDataFinish(media_data) == 1) {
                                JSONObject paramJSONObject = new JSONObject();
                                paramJSONObject.put("download_starttime", Long.toString(startTime));
                                long endTime = System.currentTimeMillis();
                                paramJSONObject.put("download_endtime", Long.toString(endTime));
                                long totalSeconds = endTime - startTime;
                                paramJSONObject.put("download_totalseconds", Long.toString(totalSeconds));
                                log.info("({})拉取分片结束耗时{}毫秒", sdkFiledDetailModel.getMsgid(), totalSeconds);
                                paramJSONObject.put("msgid", sdkFiledDetailModel.getMsgid());
                                paramJSONObject.put("clound_fileurl", sdkFiledDetailModel.getMsgid());
                                paramJSONObject.put("download_slicecount", String.valueOf(count));

                                log.info("({})开始上传.......", sdkFiledDetailModel.getMsgid());
                                long s = System.currentTimeMillis();
                                paramJSONObject.put("upload_starttime", Long.toString(s));

                                int l = 0;
                                int h1 = 0;
                                //拼接字节流集合产生 文件字节流（upb）
                                byte[] upb = new byte[byteslen + 10];
                                for (int i = 0; i < bytesList.size(); i++) {
                                    byte[] lbyte = (byte[]) bytesList.get(i);
                                    h1 = lbyte.length;
                                    System.arraycopy(lbyte, 0, upb, l, h1);
                                    l = l + h1;
                                    if (i + 1 != bytesList.size()) {
                                        byte[] len2 = (byte[]) bytesList.get(i + 1);
                                        h1 = l + len2.length;
                                    }
                                }
                                String allFileName = "";
                                /**
                                 * 上传回调
                                 */
                                try {
                                    //组装文件名msgid+后缀
                                    String msgid = sdkFiledDetailModel.getMsgid();
                                    //处理后缀名
                                    allFileName = dealSuffix(sdkFiledDetailModel.getMsgtype(), msgid);
                                    //upload(文件名,文件字节流)
                                    Response res = uploadData.upload(allFileName, upb);
                                    if(res.statusCode==200){
                                        statCode = 0;
                                    }else {
                                        statCode = 2;
                                        callback(null,ret,statCode,res);
                                        return;
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                /**
                                 * 封装回调参数
                                 */
                                long e = System.currentTimeMillis();
                                paramJSONObject.put("upload_endtime", Long.toString(e));
                                log.info("({})上传结束耗时{}毫秒", sdkFiledDetailModel.getMsgid(), e - s);
                                paramJSONObject.put("upload_totalseconds", Long.toString(e - s));
                                paramJSONObject.put("msgid", sdkFiledDetailModel.getMsgid());
                                paramJSONObject.put("clound_fileurl", allFileName);
                                paramJSONObject.put("msgtype", sdkFiledDetailModel.getMsgtype());
                                paramJSONObject.put("roomwxid", sdkFiledDetailModel.getRoomwxid());
                                paramJSONObject.put("roomname", sdkFiledDetailModel.getRoomname());
                                paramJSONObject.put("compid", sdkFiledDetailModel.getCompid());
                                paramJSONObject.put("source", sdkFiledDetailModel.getSource());
                                List<JSONObject> resultList = new ArrayList<>();
                                resultList.add(paramJSONObject);
                                //回调
                                callback(resultList,ret,statCode,null);
                                Finance.FreeMediaData(media_data);
                                break;
                            } else {
                                indexbuf = Finance.GetOutIndexBuf(media_data);
                                Finance.FreeMediaData(media_data);
                            }
                        }
                    }
                })
        );
        long ett = System.currentTimeMillis();
        System.out.println("耗时："+(ett-stt));
        return getSuccessRtn("");
    }

    /**
     * @param resultList 数据
     * @param ret 官网状态码
     * @param statCode 拉取以及上传状态码:0-下载上传都成功 1-下载失败 2-下载成功，上传失败
     * @param res 回调反馈信息
     */
    private void callback(List<JSONObject> resultList, long ret, int statCode,Response res) {
        JSONObject paramJSONObjectMap = new JSONObject();
        paramJSONObjectMap.put("code", statCode);
        paramJSONObjectMap.put("message", "WeChat-StatusCode:"+"[ "+ret+" ]"+"WeChat-Message: "+ WeChatStateCodeEnum.getMsgByStatCode(ret)+"  "+res);
        paramJSONObjectMap.put("data", resultList);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(callback_url);
        // 设置请求的header
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
        StringEntity entity = new StringEntity(paramJSONObjectMap.toString(), "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        // 执行请求
        log.info("开始回调( {} ),参数:{}", callback_url, paramJSONObjectMap);
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("回调结束，结果:{}", response);
    }

    private void setStatMsg(int statCode, String statMsg, JSONObject paramJSONObjectMap,long ret) {

        paramJSONObjectMap.put("code", "0");
        paramJSONObjectMap.put("code", "0");
    }

    private String dealSuffix(String msgtype, String msgid) {
        StringBuffer sb = new StringBuffer(msgid);
        switch (msgtype) {
            case "image":
                sb.append(".jpg");
                break;
            case "voice":
                sb.append(".amr");
                break;
            case "video":
                sb.append(".mp4");
                break;
        }
        return sb.toString();
    }

}
