package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 切换企业日志
 */
@Data
public class ChangeLogEntity implements Serializable {
    private String msgid;//	    消息id，消息的唯一标识，企业可以使用此字段进行消息去重。String类型
    private String action;//	消息动作，切换企业为switch。String类型
    private String time;//	    消息发送时间戳，utc时间，ms单位。
    private String user;//  	具体为切换企业的成员的userid。String类型
}
