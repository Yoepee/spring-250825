package com.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Spring250825Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring250825Application.class, args);
    }

}
