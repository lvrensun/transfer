package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * MarkDown格式消息
 */
@Data
public class MarkDownEntity implements Serializable {
    private String msgtype;//	markdown。String类型, 标识MarkDown消息类型
    private String content;//	markdown消息内容，目前为机器人发出的消息
}
