package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public DaoFactory daoFactoryBean() {
        return new DaoFactory();
    }
}
