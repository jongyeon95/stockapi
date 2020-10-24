package com.pretask.stockapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class StockapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockapiApplication.class, args);
    }
}
