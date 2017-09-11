package com.paopao.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.paopao"})
public class PaoPaoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaoPaoWebApplication.class, args);
    }




}
