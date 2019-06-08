package com.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import java.util.logging.Logger;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.twitter.*"})
@EntityScan("com.twitter.*")
@PropertySource(value = "file:properties/application.properties", ignoreResourceNotFound = true)

public class TwitterSearchApplication {


    public static void main(String[] args) {

        SpringApplication.run(TwitterSearchApplication.class, args);
    }
}
