package com.yang.thread.chapter15;

@FunctionalInterface
public interface Task<T> {

    T call();
}
