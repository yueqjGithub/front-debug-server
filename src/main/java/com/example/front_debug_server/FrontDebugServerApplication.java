package com.example.front_debug_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
@MapperScan(basePackages = {"com.example.front_debug_server.mapper"})
public class FrontDebugServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontDebugServerApplication.class, args);
    }

}
