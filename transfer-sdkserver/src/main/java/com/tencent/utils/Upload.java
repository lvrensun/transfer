package com.tencent.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.RemovalListener;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
@Component
public class Upload {

    @Value("${qiniuyun.accesskey}")
    private String accesskey;
    @Value("${qiniuyun.secretkey}")
    private String secretkey;
    @Value("${qiniuyun.bucket}")
    private String bucket;

    public Response upload(String key, byte[] uploadBytes) throws IOException {
        // 密钥配置
        Auth auth = Auth.create(accesskey, secretkey);
        //创建上传对象
        UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone1()));

        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(uploadBytes);
        Response res = null;
        try {
            //调用put方法上传
            res = uploadManager.put(byteInputStream, key, auth.uploadToken(bucket), null, null);
            //打印返回的信息
            //log.info("上传返回信息：{}", res);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return res;
    }

    //流获取输入的搜索关键字同行一行的语句
    public static ArrayList<String> isContainContent(String url, String keyWord) throws Exception {
        File pathname = new File(url);
        ArrayList<String> jsonObjects = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        boolean result = false;
        //行读取
        LineNumberReader lineReader = null;
        InputStreamReader read = new InputStreamReader(new FileInputStream(pathname), "gbk");
        lineReader = new LineNumberReader(read);
        String readLine = null;
        while ((readLine = lineReader.readLine()) != null) {

            //判断是否包含
            if (readLine.contains(keyWord)) {
//                result = true;
//                    jsonObject.put("lineWords",readLine);
//                    jsonObject.put("lineNumber", lineReader.getLineNumber());
                jsonObject.put("pathname", pathname);
                jsonObjects.add(readLine);
            }
        }
        //关闭流
        if (lineReader != null) {
            try {
                lineReader.close();
            } catch (IOException e) {
                e.printStackTrace();
//                lineReader = null;
            }
        }
//        jsonObject.put("flag", result);
        return jsonObjects;
    }
}