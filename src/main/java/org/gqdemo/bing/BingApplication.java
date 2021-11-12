package org.gqdemo.bing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BingApplication.class, args);
    }

}
