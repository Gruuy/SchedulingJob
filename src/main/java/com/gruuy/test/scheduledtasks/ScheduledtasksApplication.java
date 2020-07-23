package com.gruuy.test.scheduledtasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //开启定时任务
public class ScheduledtasksApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduledtasksApplication.class, args);
    }

}
