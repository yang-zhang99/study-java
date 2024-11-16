package com.yang.thread.chapter15;


public interface Observable {

    // 任务生命周期枚举
    enum Cycle {
        STARTED, RUNNING, DONE, ERROR
    }

    // 当前任务的生命周期状态
    Cycle getCycle();

    // 启动线程的方法
    void start();

    // 线程的打断方法
    void interrupt();
}