package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 音频共享文档消息
 */
@Data
public class VoipDocShareEntity implements Serializable {
    private String msgid;//	    消息id，消息的唯一标识，企业可以使用此字段进行消息去重。String类型
    private String action;//	消息动作，目前有send(发送消息)/recall(撤回消息)/switch(切换企业日志)三种类型。String类型
    private String from;//	    消息发送方id。同一企业内容为userid，非相同企业为external_userid。消息如果是机器人发出，也为external_userid。String类型
    private List<Map<String ,String>> tolist;//消息接收方列表，可能是多个，同一个企业内容为userid，非相同企业为external_userid。数组，内容为string类型
    private String msgtime;//	消息发送时间戳，utc时间，单位秒。
    private String msgtype;//	voip_doc_share。String类型, 标识音频共享文档类型
    private String voipid;//	String类型, 音频id
    private VoipDocShare voipDocShare;//	共享文档消息内容。包括filename、md5sum、filesize、sdkfileid字段。Object类型
}
