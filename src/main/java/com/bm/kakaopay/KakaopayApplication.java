package com.bm.kakaopay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

import javax.annotation.PreDestroy;

@Slf4j
@SpringBootApplication
@EnableAutoConfiguration
@AutoConfigureAfter(JacksonAutoConfiguration.class)
public class KakaopayApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakaopayApplication.class, args);
    }

    @PreDestroy
    public void destroy() {
        try {
            log.info("SHUTDOWN COMPLETE.");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
