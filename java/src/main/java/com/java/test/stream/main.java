package com.java.test.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class main {
    
    public static void main(String[] args) {
        People p = new People("John", 180,1);
        People p2 = new People("Hoh", 170,2);
        
        List<People> peoples = new ArrayList<People>();
        peoples.add(p);
        peoples.add(p2);
        
       // System.out.println(peoples.stream().Collect(Collectors.to()));
    }
}
