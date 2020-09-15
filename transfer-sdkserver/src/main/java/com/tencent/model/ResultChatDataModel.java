package com.tencent.model;

import lombok.Data;
import java.util.List;

@Data
public class ResultChatDataModel extends BaseModel{
    private String errcode;	        //0表示成功，错误返回非0错误码，需要参看errmsg。Uint32类型
    private String errmsg;	        //返回信息，如非空为错误原因。String类型
    private List<ResultChatDataDetailModel> chatdata;	    //聊天记录数据内容。数组类型。包括seq、msgid等内容
}
