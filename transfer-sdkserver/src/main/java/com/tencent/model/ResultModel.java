package com.tencent.model;

import lombok.Data;

@Data
public class ResultModel extends BaseModel {
    private String seq;
    private String msgid;
    private String publickey_ver;
    private String encrypt_random_key;
    private String encrypt_chat_msg;
    private String content;
    private String msgtype;
}
