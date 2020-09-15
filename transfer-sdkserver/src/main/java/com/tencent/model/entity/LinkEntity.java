package com.tencent.model.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
@Data
public class LinkEntity implements Serializable {
    private String msgtype;//	    链接消息为：link。String类型
    private String title;//	        消息标题。String类型
    private String description;//	消息描述。String类型
    private String link_url;//	    链接url地址。String类型
    private String image_url;//	    链接图片url。String类型
}
