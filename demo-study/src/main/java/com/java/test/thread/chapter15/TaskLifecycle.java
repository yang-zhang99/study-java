package com.yang.thread.chapter15;

public interface TaskLifecycle<T> {

    // 任务启动时会触发 onStart 方法
    void onStart(Thread thread);

    // 任务正在运行时会触发
    void onRunning(Thread thread);


    void onFinish(Thread thread, T result);


    void onError(Thread thread, Exception e);


    class EmptyLifecycle<T> implements TaskLifecycle<T> {

        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, T result) {

        }

        @Override
        public void onError(Thread thread, Exception e) {

        }
    }
}