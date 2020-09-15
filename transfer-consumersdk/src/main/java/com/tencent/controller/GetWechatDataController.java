package com.tencent.controller;

import com.alibaba.fastjson.JSONObject;
import com.tencent.model.ParamChatDataModel;
import com.tencent.model.ParamMediaDataModel;
import com.tencent.service.GetWechatDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Api(tags = "GetWechatDataController",description = "获取企业微信数据控制器")
@RestController
@RequestMapping("/fetch/wechat/content")
public class GetWechatDataController {

    @Autowired
    private GetWechatDataService getWechatDataService;

    /**
     * 获取微信公众号文本信息
     * @param paramChatDataModel
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "接口功能:  获取会话记录数据",notes = "注意项:一次拉取调用上限1000条会话记录，可以通过分页拉取的方式来依次拉取。调用频率不可超过600次/分钟")
    @RequestMapping(value = "/getchatdata",method = RequestMethod.POST )
    public Map<String,Object> getChatData(@RequestBody String paramChatDataModel){
        ParamChatDataModel param = JSONObject.parseObject(paramChatDataModel, ParamChatDataModel.class);
        log.info("enter consumer ------------>");
        log.info("request parameter: {}",paramChatDataModel);
        return getWechatDataService.getChatData(param);
    }
    /**
     * 获取微信公众号媒体信息（包括：视频
     * 名片
     * 位置
     * 表情...等等）
     * @param param
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "接口功能:  获取微信公众号媒体信息",notes = "获取信息:获取微信公众号媒体信息(包括：视频、名片、位置、 表情...等等)")
    @RequestMapping(value = "/getmediadata",method = RequestMethod.POST )
    public Map<String,Object> getMediaData(@RequestBody String param){
        ParamMediaDataModel paramMediaDataModel = JSONObject.parseObject(param, ParamMediaDataModel.class);
        log.info("enter consumer ------------>");
        log.info("request parameter: {}",param);
        return getWechatDataService.getMediaData(paramMediaDataModel);
    }
}
