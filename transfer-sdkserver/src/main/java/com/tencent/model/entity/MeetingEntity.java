package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class MeetingEntity implements Serializable {
    private String msgtype;//	meeting。String类型
    private String topic;//	    会议主题。String类型
    private String starttime;//	会议开始时间。Utc时间
    private String endtime;//	会议结束时间。Utc时间
    private String address;//	会议地址。String类型
    private String remarks;//	会议备注。String类型
    private String meetingtype;//	会议消息类型。101发起会议邀请消息、102处理会议邀请消息。Uint32类型
    private String meetingid;//	会议id。方便将发起、处理消息进行对照。uint64类型
    private String status;//	会议邀请处理状态。1 参加会议、2 拒绝会议、3 待定、4 未被邀请、5 会议已取消、6 会议已过期、7 不在房间内。Uint32类型
}
