package com.tencent.controller;


import com.alibaba.fastjson.JSONObject;
import com.tencent.constants.MsgType;
import com.tencent.model.*;
import com.tencent.service.FetchWeChatContentService;
import com.tencent.utils.CheckUtil;
import com.tencent.utils.RSAUtil;
import com.tencent.utils.Upload;
import com.tencent.wework.Finance;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/fetch/wechat/content")
public class FetchWeChatContentController extends BaseController {
    @Resource
    private FetchWeChatContentService fetchWeChatContentService;


    @RequestMapping(value = "/getchatdata", method = RequestMethod.POST)
    public Map<String, Object> GetChatData(@RequestBody ParamChatDataModel param) {
        /**
         * 传参判断
         */
        if (CheckUtil.isEmpty(param)) {
            return getFailRtn("请求参数不能为空");
        }
        if (CheckUtil.isEmpty(param.getSeq())) {
            return getFailRtn("请求参数( seq )不能为空!");
        }
        if (CheckUtil.isEmpty(param.getLimit())) {
            return getFailRtn("请求参数( limit )不能为空");
        }
        if (CheckUtil.isEmpty(param.getTimeout())) {
            return getFailRtn("请求参数( timeout )不能为空");
        }
        if (CheckUtil.isEmpty(param.getCorpsecret())) {
            return getFailRtn("请求参数( corpsecret )不能为空");
        }
        if (CheckUtil.isEmpty(param.getCorpid())) {
            return getFailRtn("请求参数( corpid )不能为空");
        }
        if (CheckUtil.isEmpty(param.getPriKey())) {
            return getFailRtn("请求参数( prikey )不能为空");
        }
        return fetchWeChatContentService.getChatData(param);
    }


    /**
     * @param param
     * @return
     * @tittle 拉取媒体消息
     */
    @RequestMapping(value = "/getmediadata", method = RequestMethod.POST)
    public Map<String, Object> getMediaData(@RequestBody ParamMediaDataModel param) {

        /**
         * 传参判断
         */
        if (CheckUtil.isEmpty(param)) {
            return getFailRtn("请求参数不能为空");
        }
        if (CheckUtil.isEmpty(param.getCorpsecret())) {
            return getFailRtn("请求参数( corpsecret )不能为空");
        }
        if (CheckUtil.isEmpty(param.getCorpid())) {
            return getFailRtn("请求参数( corpid )不能为空");
        }
        if (CheckUtil.isEmpty(param.getTimeout())) {
            return getFailRtn("请求参数( timeout )不能为空");
        }
        if (CheckUtil.isEmpty(param.getSdkFiledDetailModelList())) {
            return getFailRtn("请求参数( SdkFiledDetailModel )不能为空");
        }
        fetchWeChatContentService.getMediaData(param);
        return getSuccessRtn("");
    }

}
