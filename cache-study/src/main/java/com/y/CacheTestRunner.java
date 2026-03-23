package com.y;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class CacheTestRunner implements CommandLineRunner {

    @Autowired
    SpringCacheTest springCache;

    @Override
    public void run(String... args) throws InterruptedException {
//        String key = "scene_001";
//        // 第一次调用：应该执行方法体（打印日志）
//        System.out.println("第一次调用...");
//        for (int i = 0; i < 100; i++) {
//            System.out.println(springCache.test(key, 50));
//        }
        String key = "scene_multi_thread";
        int threadCount = 100;

        // 创建一个固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 使用 CountDownLatch 确保所有线程同时开始“冲刺”，模拟高并发
        CountDownLatch latch = new CountDownLatch(1);

        System.out.println("=== 多线程并发测试开始 ===");

        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    latch.await(); // 等待发令枪响
                    String result = springCache.test(key, 50);
                    // 只有前几个或者特定线程打印结果，避免刷屏
                        System.out.println("线程 " + Thread.currentThread().getName() + " 获取结果: " + result);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        latch.countDown(); // 发令枪响：所有线程同时调用
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("=== 多线程并发测试结束 ===");
    }

}
