package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class BaseEntity<T> implements Serializable {
    private String seq;
    private String msgid;
    private String publickey_ver;
    private String encrypt_random_key;
    private String encrypt_chat_msg;
    private  T t;
    private String msgtype;

}
