package com.intreswitch.articleblogsystemintv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.intreswitch.articleblogsystemintv",
        "com.intreswitch.articleblogsystemintv.entities"})
public class ArticleBlogSystemIntVApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleBlogSystemIntVApplication.class, args);
    }

}
