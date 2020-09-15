package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class ImageEntity implements Serializable {
    private String msgtype;//	图片消息为：image。String类型
    private String sdkfileid;//	媒体资源的id信息。String类型
    private String md5sum;//	图片资源的md5值，供进行校验。String类型
    private String filesize;//	图片资源的文件大小。Uint32类型
}
