package com.tencent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TransferEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransferEurekaApplication.class, args);
    }

}
