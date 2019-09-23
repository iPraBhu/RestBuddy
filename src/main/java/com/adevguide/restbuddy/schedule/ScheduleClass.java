package com.adevguide.restbuddy.schedule;

import org.springframework.scheduling.annotation.Scheduled;

import com.adevguide.restbuddy.RestbuddyApplication;

/**
 * @author PraBhu
 *
 */
public class ScheduleClass {
    
    @Scheduled(cron = "0 0 * * * ?")
    public void restartApp() {
        RestbuddyApplication.restart();
    }

}
