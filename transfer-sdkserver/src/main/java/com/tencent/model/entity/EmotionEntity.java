package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class EmotionEntity implements Serializable {
    private String msgtype;//	表情消息为：emotion。String类型
    private String type;//	    表情类型，png或者gif.1表示gif 2表示png。Uint32类型
    private String width;//	    表情图片宽度。Uint32类型
    private String height;//	表情图片高度。Uint32类型
    private String sdkfileid;//	媒体资源的id信息。String类型
    private String md5sum;//	资源的md5值，供进行校验。String类型
    private String imagesize;//	资源的文件大小。Uint32类型
}
