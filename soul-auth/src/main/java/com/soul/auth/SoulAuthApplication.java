package com.soul.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.soul.*"})
public class SoulAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoulAuthApplication.class, args);
    }
}
