package com.paopao.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.paopao"})
public class PaoPaoWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PaoPaoWebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PaoPaoWebApplication.class);
    }
}
