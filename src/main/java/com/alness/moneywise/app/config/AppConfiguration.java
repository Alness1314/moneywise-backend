package com.alness.moneywise.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.alness.moneywise.app.service.AppConfigService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AppConfiguration {
    @Autowired
    private AppConfigService appConfigService;
    
    @PostConstruct
    public void init(){
        log.info("Response: {}", appConfigService.createDefaultValues());
    }
}
