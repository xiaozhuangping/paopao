package com.paopao.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EntityScan("com.paopao.sql")
@EnableJpaRepositories(basePackages={"com.paopao.sql.dao"})
@SpringBootApplication
public class PaoPaoImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaoPaoImplApplication.class, args);
    }

}
