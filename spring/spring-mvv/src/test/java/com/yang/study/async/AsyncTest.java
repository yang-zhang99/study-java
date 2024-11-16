package com.yang.study.async;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AsyncTest {
    
    @Autowired
    private AsyncSystem asyncTest;
    
    @Test
    void out() {
        asyncTest.testAsync();
    }
}
