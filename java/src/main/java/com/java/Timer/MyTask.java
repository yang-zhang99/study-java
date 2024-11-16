package com.java.Timer;

import java.util.TimerTask;

public class MyTask extends TimerTask {
    
    @Override
    public void run() {
        System.out.println("MyTask 任务执行时间为 " + System.currentTimeMillis());
    }
}
