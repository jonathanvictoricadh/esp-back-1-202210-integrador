package com.dh.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ApiStudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiStudentApplication.class, args);
    }

}
