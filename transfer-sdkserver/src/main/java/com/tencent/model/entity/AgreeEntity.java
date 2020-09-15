package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class AgreeEntity implements Serializable {
    private String msgtype;//	同意消息为：agree，不同意消息为：disagree。String类型
    private String userid;//	同意/不同意协议者的userid，外部企业默认为external_userid。String类型
    private String agree_time;//	同意/不同意协议的时间，utc时间，ms单位。
}
