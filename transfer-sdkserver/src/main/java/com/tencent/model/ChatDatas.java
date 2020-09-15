package com.tencent.model;

import lombok.Data;

@Data
public class ChatDatas extends BaseModel{
    private long seq;
    private String msgid;
    private String publickey_ver;
    private String encrypt_random_key;
    private String encrypt_chat_msg;
}
