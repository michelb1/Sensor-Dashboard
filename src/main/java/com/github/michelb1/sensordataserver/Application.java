package com.github.michelb1.sensordataserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

@Configuration
@PropertySource("classpath:service-local.properties")
@PropertySource(value = "classpath:service.properties", ignoreResourceNotFound = true)
class ServiceConfig {
}

@Configuration
@PropertySource(value = "classpath:server.properties", ignoreResourceNotFound = true)
class ServerConfig {
}
