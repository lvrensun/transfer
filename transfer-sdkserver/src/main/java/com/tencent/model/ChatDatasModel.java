package com.tencent.model;

import lombok.Data;

import java.util.List;

/**
 * 聊条记录实体
 */
@Data
public class ChatDatasModel extends BaseModel {
    private String errcode;
    private String errmsg;
    private List<ChatDatas> chatdata;
}
