package com.starter.application;

import org.log.annotation.CustomLogging;
import org.log.annotation.TimeMonitoring;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LogDemo implements CommandLineRunner {

    @Override
    @TimeMonitoring
    @CustomLogging
    public void run(String... args) throws Exception {
        System.out.println("Ждём");
        sleep(3000);
    }

    public static void sleep(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
