package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 日程消息
 */
@Data
public class CalenderEntity implements Serializable {
    private String msgtype;//	    calendar。String类型, 标识日程消息类型
    private String title;//	        日程主题。String类型
    private String creatorname;//	日程组织者。String类型
    private String attendeename;//	日程参与人。数组，内容为String类型
    private String starttime;//	    日程开始时间。Utc时间，单位秒
    private String endtime;//	    日程结束时间。Utc时间，单位秒
    private String place;//	        日程地点。String类型
    private String remarks;//	    日程备注。String类型
}
