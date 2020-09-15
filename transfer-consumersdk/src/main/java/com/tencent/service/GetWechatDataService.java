package com.tencent.service;

import com.tencent.model.ParamChatDataModel;
import com.tencent.model.ParamMediaDataModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


@FeignClient(value = "transfer-sdkserver")
public interface GetWechatDataService {

    @RequestMapping(value = "/fetch/wechat/content/getchatdata",method = RequestMethod.POST )
    Map<String, Object> getChatData(@RequestBody ParamChatDataModel param);

    @RequestMapping(value = "/fetch/wechat/content/getmediadata",method = RequestMethod.POST )
    Map<String, Object> getMediaData(@RequestBody ParamMediaDataModel param);

}



