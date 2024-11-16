package com.java.string;

import java.util.Arrays;

public class StringTest {
    
    public static void main(String[] args) {
        StringTest.trimTest();
    }
    
    /**
     * 去空格
     */
    private static void trimTest() {
        String str = " Hello World!";
        System.out.println(str);
        System.out.println(str.trim());
        System.out.println(Arrays.stream(str.split("\\s+")).reduce("", (a, b) -> a + b));
    }
    
}
