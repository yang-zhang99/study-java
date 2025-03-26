package com.yang.nacos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupTaskRunner implements CommandLineRunner {
    
    private static final Log LOG = LogFactory.getLog(StartupTaskRunner.class);
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started! Running startup task...");
        // 在这里执行初始化任务
        for (int i = 0; i < 200; i++) {
            try {
                Thread.sleep(200); // 线程休眠指定时间
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            new Thread(() -> {
                int minSleepTime = 4000000; // 最小休眠时间：4秒（4000毫秒）
                LOG.warn("线程进行中");
                try {
                    Thread.sleep(minSleepTime); // 线程休眠指定时间
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
