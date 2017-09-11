package com.paopao.web;

import com.paopao.web.config.shiro.MyExceptionResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.paopao"})
public class PaoPaoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaoPaoWebApplication.class, args);
    }




}
