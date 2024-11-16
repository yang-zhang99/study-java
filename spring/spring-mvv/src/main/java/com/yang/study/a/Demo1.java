package com.yang.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Demo1 {
    final static Logger logger = LoggerFactory.getLogger(Demo1.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("Hello world!");
        
        System.out.println(Math.random() * 10);

//        for (int i = 0; i < 1000; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1000);
//                        logger.info("------");
//                        Thread.sleep(1000);
//                        throw new Exception("---");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
//
//        Thread.sleep(100000000);
    }
}
