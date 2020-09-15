package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class VoteEntity implements Serializable {
    private String msgtype;//	vote。String类型
    private String votetitle;//	投票主题。String类型
    private String voteitem;//	投票选项，可能多个内容。String数组
    private String votetype;//	投票类型.101发起投票、102参与投票。Uint32类型
    private String voteid;//	投票id，方便将参与投票消息与发起投票消息进行前后对照。String类型
}
