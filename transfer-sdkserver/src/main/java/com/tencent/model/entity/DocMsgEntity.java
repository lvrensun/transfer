package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 在线文档消息
 */
@Data
public class DocMsgEntity implements Serializable {
    private String msgtype;//	    docmsg。String类型, 标识在线文档消息类型
    private String title;//	        在线文档名称
    private String link_url;//	    在线文档链接
    private String doc_creator;//	在线文档创建者。本企业成员创建为userid；外部企业成员创建为external_userid
}
