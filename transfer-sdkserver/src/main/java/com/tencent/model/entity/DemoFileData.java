package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class DemoFileData implements Serializable {
    private String filename;//	    文档共享名称。String类型
    private String demooperator;//	文档共享操作用户的id。String类型
    private String starttime;//	    文档共享开始时间。Uint32类型
    private String endtime;//	    文档共享结束时间。Uint32类型
}
