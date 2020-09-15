package com.tencent.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class MeetingVoiceCall implements Serializable {
    private String endtime;//	    音频结束时间。uint32类型
    private String sdkfileid;//	    sdkfileid。音频媒体下载的id。String类型
    private List<DemoFileData> demofiledata;
    private List<ShareScreenData> sharescreendata;
}
