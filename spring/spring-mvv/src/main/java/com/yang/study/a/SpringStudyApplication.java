package com.yang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
public class SpringStudyApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SpringStudyApplication.class, args);
    }
    
    final ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10, 120, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(2000), new ThreadPoolExecutor.DiscardPolicy());
    
    @GetMapping
    public String demo() throws InterruptedException {
        System.out.println(LocalDateTime.now() + " --- " + Thread.currentThread().getName() + "Start");
        //        TimeUnit.SECONDS.sleep((long) (Math.random() * 100));
        
        List<Future<String>> list = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            Future<String> f = executor.submit(new Task<>());
        }
        
        for (Future<String> f : list) {
            try {
                System.out.println(f.get());
            } catch (ExecutionException e) {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        }
        
        System.out.println(LocalDateTime.now() + " --- " + Thread.currentThread().getName() + "End");
        return "It Work";
    }
    
}
