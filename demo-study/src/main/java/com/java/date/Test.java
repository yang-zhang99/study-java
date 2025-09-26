package com.java.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test {
    
    public static void main(String[] args) {
        String date1 = "2021年5月29日";
        String date2 = "2021年12月29日";
        
        DateTimeFormatter originalFormat = DateTimeFormatter.ofPattern("yyyy年M月d日");
        DateTimeFormatter standardFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate1 = LocalDate.parse(date1, originalFormat);
        System.out.println(parsedDate1.format(standardFormat));
        LocalDate parsedDate2 = LocalDate.parse(date2, originalFormat);
        System.out.println(parsedDate2.format(standardFormat));
    }
    
}
