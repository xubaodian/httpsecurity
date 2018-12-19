package com.server.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@SpringBootApplication
//添加这个注解，过滤器才能生效
@ServletComponentScan
public class HttpApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpApplication.class, args);
    }

    //自定义cookie
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        //客户端session 名称
        serializer.setCookieName("token"); // <1>
        //路径
        serializer.setCookiePath("/"); // <2>
        //设置cookie有效域
        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$"); // <3>
        return serializer;
    }
}
