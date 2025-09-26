package com.java;

import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {
    
    public static void main(String[] args) {
        
        Consumer<String> printConsumer = (input) -> System.out.println(input);
        // 1
        printConsumer.accept("Hello, World!");
        // 2
        List<String> list = List.of("Hello, World!", "China", "Everyone");
        list.forEach(printConsumer);
        
    }
    
}
