package com.java.spring;

public class Boot {
    
    private final int number;
    
    public Boot(int number) {
        this.number = number;
    }
    
    public static void run(Class<?> primarySource) {
        new Boot(1).run();
    }
    
    public void run() {
        System.out.println(number);
    }
    
    
    public static void main(String[] args) {
        Boot.run(Boot.class);
    }
    
    
}
