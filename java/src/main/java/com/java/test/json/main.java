package com.java.test.json;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class main {

    public static void main(String[] args) {

        System.out.println(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );

    }
}
