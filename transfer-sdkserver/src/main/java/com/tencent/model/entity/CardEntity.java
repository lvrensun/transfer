package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class CardEntity implements Serializable {
    private String msgtype;//	名片消息为：card。String类型
    private String corpname;//	名片所有者所在的公司名称。String类型
    private String userid;//	名片所有者的id，同一公司是userid，不同公司是external_userid。String类型
}
