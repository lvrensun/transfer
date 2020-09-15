package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 图文消息
 */
@Data
public class NewEntity implements Serializable {
    private String msgtype;//	    news。String类型, 标识图文消息类型
    private String info;//	        图文消息的内容
    private String item;//	        图文消息数组，每个item结构包含title、description、url、picurl等结构
    private String title;//	        图文消息标题。String类型
    private String description;//	图文消息描述。String类型
    private String url;//	        图文消息点击跳转地址。String类型
    private String picurl;//	    图文消息配图的url。String类型
}
