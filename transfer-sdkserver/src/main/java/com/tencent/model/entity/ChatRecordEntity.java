package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class ChatRecordEntity implements Serializable {
    private String msgtype;//	消息为：chatrecord。String类型
    private String title;//	    聊天记录标题。String类型
    private String item;//	    消息记录内的消息内容，批量数据
}
