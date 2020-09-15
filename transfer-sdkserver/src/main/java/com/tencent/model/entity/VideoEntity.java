package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class VideoEntity implements Serializable {
    private String msgtype;//	    视频消息为：video。String类型
    private String sdkfileid;//	    媒体资源的id信息。String类型
    private String md5sum;//	    资源的md5值，供进行校验。String类型
    private String filesize;//	    资源的文件大小。Uint32类型
    private String play_length;//	视频播放长度。Uint32类型
}
