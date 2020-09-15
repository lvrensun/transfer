package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class RedPacketEntity implements Serializable {
    private String msgtype;//	    redpacket。String类型
    private String type;//	        红包消息类型。1 普通红包、2 拼手气群红包、3 激励群红包。Uint32类型
    private String wish;//	        红包祝福语。String类型
    private String totalcnt;//	    红包总个数。Uint32类型
    private String totalamount;//	红包总金额。Uint32类型，单位为分。
}
