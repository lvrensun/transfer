package com.tencent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class TransferSdkserverApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransferSdkserverApplication.class, args);
    }

}
