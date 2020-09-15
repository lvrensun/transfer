package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class CollectEntity implements Serializable {
    private String msgtype;//	    collect。String类型
    private String room_name;//	    填表消息所在的群名称。String类型
    private String creator;//	    创建者在群中的名字。String类型
    private String create_time;//	创建的时间。String类型
    private String title;//	        表名。String类型
    private String details;//	    表内容。json数组类型
    private String id;//	        表项id。Uint64类型
    private String ques;//	        表项名称。String类型
    private String type;//	        表项类型，有Text(文本),Number(数字),Date(日期),Time(时间)。String类型
}
