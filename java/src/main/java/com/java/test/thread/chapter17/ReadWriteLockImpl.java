package com.yang.thread.chapter17;

public class ReadWriteLockImpl implements ReadWriteLock {

    private final Object MUTEX = new Object();
    // 当前有多少个线程正在写入
    private int writingWriters = 0;
    // 当前有多少个线程正在等待写入
    private int waitingWriters = 0;
    // 当前有多少个线程正在 read
    private int readingReaders = 0;
    // read 和 writer 的偏好设置
    private boolean preferWriter;

    public ReadWriteLockImpl() {
        this(true);
    }

    public ReadWriteLockImpl(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }


    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    void incrementWritingWriters() {
        this.writingWriters++;
    }

    void incrementWaitingWriters() {
        this.waitingWriters++;
    }

    void incrementReadingReaders() {
        this.readingReaders++;
    }

    void decrementWritingWriters() {
        this.writingWriters--;
    }

    void decrementWaitingWriters() {
        this.waitingWriters--;
    }

    void decrementReadingReaders() {
        this.readingReaders--;
    }

    public int getWritingWriters() {
        return this.writingWriters;
    }

    @Override
    public int getWaitingWriters() {
        return this.waitingWriters;
    }

    @Override
    public int getReadingReaders() {
        return this.readingReaders;
    }

    Object getMUTEX() {
        return this.MUTEX;
    }

    boolean getPreferWriter() {
        return this.preferWriter;
    }

    void changePreferWriter(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }
}
