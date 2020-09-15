package com.tencent.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "qiniuyun")
@Component
@Data
public class QiNiuCloudConfig {
    private String accesskey;
    private String secretkey;
    private String bucket;
}
