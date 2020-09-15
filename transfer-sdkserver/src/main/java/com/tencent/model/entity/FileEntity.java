package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class FileEntity implements Serializable {
    private String msgtype;//	文件消息为：file。String类型
    private String sdkfileid;//	媒体资源的id信息。String类型
    private String md5sum;//	资源的md5值，供进行校验。String类型
    private String filename;//	文件名称。String类型
    private String fileext;//	文件类型后缀。String类型
    private String filesize;//	文件大小。Uint32类型
}
