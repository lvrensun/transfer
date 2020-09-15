package com.tencent.model;

public enum WeChatStateCodeEnum {

    S10000(10000, "【说明】: 请求参数错误，【建议】: 检查Init接口corpid、secret参数；检查GetChatData接口limit参数是否未填或大于1000；检查GetMediaData接口sdkfileid是否为空，indexbuf是否正常"),
    S10001(10001, "【说明】: 网络请求错误，【建议】: 检查是否网络有异常、波动；检查使用代理的情况下代理参数是否设置正确的用户名与密码"),
    S10002(10002, "【说明】: 数据解析失败，【建议】: 建议重试请求。若仍失败，可以反馈给企业微信进行查询，请提供sdk接口参数与调用时间点等信息"),
    S10003(10003, "【说明】: 系统调用失败，【建议】: GetMediaData调用失败，建议重试请求。若仍失败，可以反馈给企业微信进行查询，请提供sdk接口参数与调用时间点等信息"),
    S10004(10004, "【说明】: 已废弃，【建议】: 目前不会返回此错误码"),
    S10005(10005, "【说明】: fileid错误，【建议】: 检查在GetMediaData接口传入的sdkfileid是否正确"),
    S10006(10006, "【说明】: 解密失败，【建议】: 请检查是否先进行base64decode再进行rsa私钥解密，再进行DecryptMsg调用"),
    S10007(10007, "【说明】: 已废弃，【建议】: 目前不会返回此错误码"),
    S10008(10008, "【说明】: DecryptMsg错误，【建议】: 建议重试请求。若仍失败，可以反馈给企业微信进行查询，请提供sdk接口参数与调用时间点等信息"),
    S10009(10009, "【说明】: ip非法，【建议】: 请检查sdk访问外网的ip是否与管理端设置的可信ip匹配，若不匹配会返回此错误码"),
    S10010(10010, "【说明】: 请求的数据过期，【建议】: 用户欲拉取的数据已过期，仅支持近3天内的数据拉取"),
    S10011(10011, "【说明】: ssl证书错误，【建议】: 使用openssl版本sdk，校验ssl证书失败");

    private  long statCode;
    private  String message;

    WeChatStateCodeEnum(long i, String s) {
    }

    public long getStatCode() {
        return statCode;
    }

    public String getMessage() {
        return message;
    }

    public static String getMsgByStatCode(long statCode) {
        for (WeChatStateCodeEnum enums : WeChatStateCodeEnum.values()) {
            if (enums.getStatCode()==statCode) {
                return enums.getMessage();
            }
        }
        return "";
    }

}
