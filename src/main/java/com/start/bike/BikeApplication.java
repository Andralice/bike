package com.start.bike;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.start.bike.mapper")
public class BikeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BikeApplication.class, args);
    }

}
