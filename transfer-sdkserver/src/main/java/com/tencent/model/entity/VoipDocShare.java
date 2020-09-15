package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class VoipDocShare implements Serializable {
    private String filename;//	文档共享文件名称。String类型
    private String md5sum;//	共享文件的md5值。String类型
    private String filesize;//	共享文件的大小。Uint64类型
    private String sdkfileid;//	共享文件的sdkfile，通过此字段进行媒体数据下载。String类型
}
