package com.tencent.service;


import com.tencent.model.ParamChatDataModel;
import com.tencent.model.ParamMediaDataModel;

import java.util.Map;

public interface FetchWeChatContentService {

    Map<String, Object> getChatData(ParamChatDataModel param);

    Map<String, Object> getMediaData(ParamMediaDataModel param);
}
