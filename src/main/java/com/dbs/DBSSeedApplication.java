package com.dbs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "DBS Seed APIs", version = "1.0", description = "DBS Seed Hackathon Project"))
public class DBSSeedApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(DBSSeedApplication.class, args);
    }
}
