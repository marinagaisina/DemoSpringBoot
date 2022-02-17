package com.marinagaisina.casestudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.marinagaisina.casestudy.database")
public class DatabaseConfig {
}
