package com.yang.thread.chapter23;

public abstract class Latch {

    protected int limit;

    public Latch(int limit) {
        this.limit = limit;
    }

    public abstract int getUnarrived();

    public abstract void await() throws InterruptedException;

    public abstract void countDown();


}
