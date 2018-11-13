package com.leif.eurekagatewayclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaGatewayClientApplication {


    public static void main(String[] args) {
        SpringApplication.run(EurekaGatewayClientApplication.class, args);
    }
}
