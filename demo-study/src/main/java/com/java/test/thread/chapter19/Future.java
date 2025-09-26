package com.yang.thread.chapter19;

public interface Future<T> {

    T get() throws InterruptedException;

    boolean done();
}
