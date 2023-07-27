package com.example.salonmanage;

import com.example.salonmanage.FIle.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class SemoconectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SemoconectApplication.class, args);
    }

}
