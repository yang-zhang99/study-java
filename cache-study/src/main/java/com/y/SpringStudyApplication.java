package com.y;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
public class SpringStudyApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringStudyApplication.class, args);
    }

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager manager = new CaffeineCacheManager("UCS_SCENE_SEND_TOTAL_COUNT_CACHE");
        manager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(100)
                .recordStats());
        return manager;
    }
}
