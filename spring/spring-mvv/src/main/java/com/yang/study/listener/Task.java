package com.yang.study.listener;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task<String> implements Callable<java.lang.String> {
    
    
    @Override
    public java.lang.String call() throws Exception {
        TimeUnit.SECONDS.sleep((long) (Math.random() * 100));
        System.out.println("Hello World");
        return "Hello World ";
    }
}
