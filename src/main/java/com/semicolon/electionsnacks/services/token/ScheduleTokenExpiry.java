package com.semicolon.electionsnacks.services.token;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

//@Configuration
@EnableScheduling
@AllArgsConstructor
public class ScheduleTokenExpiry {
    TokenService tokenService;

    @Scheduled(cron = "0 10 0 * * *")
    public void tokenExpiredAt(){
        System.out.println("Deleted");
        tokenService.deleteExpiredToken();
    }
}
