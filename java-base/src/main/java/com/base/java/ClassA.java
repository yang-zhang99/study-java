package com.base.java;

import org.springframework.stereotype.Component;

@Component
public class ClassA implements ClassI {
    
    public String send(Integer i) {
        System.out.println("ClassA");
        return null;
    }
}
