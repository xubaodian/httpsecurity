package com.server.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HttpApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpApplication.class, args);
    }
}
