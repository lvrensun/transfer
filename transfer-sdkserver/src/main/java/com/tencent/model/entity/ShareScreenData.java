package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class ShareScreenData implements Serializable {
    private String share;//	            屏幕共享用户的id。String类型
    private String starttime;//	        屏幕共享开始时间。Uint32类型
    private String endtime;//	        屏幕共享结束时间。Uint32类型
}
