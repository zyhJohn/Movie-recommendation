package com.zucc.zyh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zucc.zyh.dao")
public class finalApplication {

    public static void main(String[] args) {
        SpringApplication.run(finalApplication.class, args);
    }

}
