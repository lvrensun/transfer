package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class ToDoEntity implements Serializable {
    private String msgtype;//	todo。String类型
    private String title;//	代办的来源文本。String类型
    private String content;//	代办的具体内容。String类型
}
