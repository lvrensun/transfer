package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class LocationEntity implements Serializable {
    private String msgtype;//	位置消息为：location。String类型
    private String longitude;//	经度，单位double
    private String latitude;//	纬度，单位double
    private String address;//	地址信息。String类型
    private String title;// 	位置信息的title。String类型
    private String zoom;//	    缩放比例。Uint32类型
}
