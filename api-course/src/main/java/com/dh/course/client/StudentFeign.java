package com.dh.course.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-student")
public interface StudentFeign {
    @CircuitBreaker(name = "clientStudent", fallbackMethod = "getByIdFallback")
    @Retry(name = "clientStudent")
    @GetMapping("/api/v1/students/{id}")
    Student getById(@PathVariable Long id);

    default Student getByIdFallback(@PathVariable Long id){
        return null;
    }

    @Getter
    @Setter
    class Student {
        private Long studentId;
        private String name;
        private String lastName;
    }
}
