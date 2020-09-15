package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class RevokeEntity implements Serializable {
    private String msgtype;//	撤回消息为：revoke。String类型
    private String pre_msgid;//	标识撤回的原消息的msgid。String类型
}
