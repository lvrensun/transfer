package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class TextEntity implements Serializable {
    private String msgid;//	    消息id，消息的唯一标识，企业可以使用此字段进行消息去重。String类型
    private String action;//	消息动作，目前有send(发送消息)/recall(撤回消息)/switch(切换企业日志)三种类型。String类型
    private String from;//	    消息发送方id。同一企业内容为userid，非相同企业为external_userid。消息如果是机器人发出，也为external_userid。String类型
    private String[] tolist;//	消息接收方列表，可能是多个，同一个企业内容为userid，非相同企业为external_userid。数组，内容为string类型
    private String roomid;//	群聊消息的群id。如果是单聊则为空。String类型
    private String msgtime;//	消息发送时间戳，utc时间，ms单位。
    private String msgtype;//	文本消息为：text。String类型
    private List<Map<String,String>> text;//	消息内容。String类型
}
