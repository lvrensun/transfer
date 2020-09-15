package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class WeAppEntity implements Serializable {
    private String msgtype;//	    消息为：weapp。String类型
    private String title;//	        消息标题。String类型
    private String description;//	消息描述。String类型
    private String username;//	    用户名称。String类型
    private String displayname;//	小程序名称。String类型
}
