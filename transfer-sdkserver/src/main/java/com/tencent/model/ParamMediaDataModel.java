package com.tencent.model;

import lombok.Data;

import java.util.List;

@Data
public class ParamMediaDataModel extends BaseModel{
    private String corpid;//	是      调用企业的企业id
    private String corpsecret;//是      平台Secret
    private String proxy;//             使用代理的请求，需要传入代理的链接。如：socks5://10.0.0.1:8081 或者 http://10.0.0.1:8081
    private String passwd;//            代理账号密码，需要传入代理的账号密码。如 user_name:passwd_123
    private String timeout;
    private List<SdkFiledDetailModel> sdkFiledDetailModelList;

}
