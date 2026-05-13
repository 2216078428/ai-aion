package com.aion.logos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * AI-Aion 灵识层启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LogosApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogosApplication.class, args);
        System.out.println("--- AI-Aion 灵识层 (The Logos Layer) 已觉醒 ---");
    }
}
