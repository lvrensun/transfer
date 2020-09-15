package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class VoiceEntity implements Serializable {
    private String msgtype;//	    语音消息为：voice。String类型
    private String voice_size;//	语音消息大小。Uint32类型
    private String play_length;//	播放长度。Uint32类型
    private String sdkfileid;// 	媒体资源的id信息。String类型
    private String md5sum;//	    资源的md5值，供进行校验。String类型
}
