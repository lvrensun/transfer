package com.tencent.model;

import lombok.Data;

@Data
public class SdkFiledDetailModel extends BaseModel {
    public String msgtype;//            消息类型(图片，语音，地址，视频等)，String类型
    public String msgid;//     是       从GetChatData返回的聊天消息中，媒体消息包括的msgid
    public String sdkfiled;//  是       从GetChatData返回的聊天消息中，媒体消息包括的sdkfileid
    public String roomwxid;//           群聊消息的群id。如果是单聊则为空。String类型
    public String roomname;//           群名称。String类型
    public String compid;
    public String source;
}
