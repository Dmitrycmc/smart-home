package com.example.springclouddiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringCloudDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudDiscoveryApplication.class, args);
    }

}
