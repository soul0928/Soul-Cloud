package com.soul.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SoulUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoulUserApplication.class, args);
    }

}
