package com.yang.study.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class AsyncSystem {
    public static final Logger LOGGER = LoggerFactory.getLogger(AsyncSystem.class);
    
    @Async
    public void testAsync() {
        LOGGER.info("Async test");
    }
}
