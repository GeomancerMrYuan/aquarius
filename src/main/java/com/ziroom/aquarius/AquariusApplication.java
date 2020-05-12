package com.ziroom.aquarius;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.ziroom.aquarius.system.mapper")
public class AquariusApplication {

    public static void main(String[] args) {
        SpringApplication.run(AquariusApplication.class, args);
    }
}
