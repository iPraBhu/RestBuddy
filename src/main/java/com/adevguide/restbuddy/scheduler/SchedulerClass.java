package com.adevguide.restbuddy.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.adevguide.restbuddy.RestbuddyApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * @author PraBhu
 *
 */
@Component
@Slf4j
public class SchedulerClass {
    

    //Restart Application at 12AM
    @Scheduled(cron = "0 0 0 * * ?")
    public void restartApp() {
        log.info("Restarting Application");
        RestbuddyApplication.restart();
    }

}
