package com.mitrais.dbassocdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DbAssocDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbAssocDemoApplication.class, args);
    }
}
