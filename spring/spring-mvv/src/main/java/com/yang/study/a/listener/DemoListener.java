package com.yang.study.listener;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DemoListener implements ApplicationListener<WebServerInitializedEvent> {
    
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        System.out.println(LocalDateTime.now() + " --- " + event.getSource() + "Start");
    }
}
