package com.ziroom.aquarius;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AquariusApplication {

    public static void main(String[] args) {
        SpringApplication.run(AquariusApplication.class, args);
    }

}
