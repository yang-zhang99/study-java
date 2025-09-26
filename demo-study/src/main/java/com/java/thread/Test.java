package com.java.thread;

import java.util.concurrent.*;

public class Test {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

//        Demo demo = new Demo();
//
//        Demo3 demo3 = new Demo3();
//        demo3.day();
//
//        Demo3.day2();

        final ExecutorService HANDLE_ORDER_EXECUTOR = Executors.newFixedThreadPool(15);


        Future<String> l;

        Callable<String> rtu = new Handler();

        l = HANDLE_ORDER_EXECUTOR.submit(rtu);

        System.out.println("end1 ---");


        String r = l.get();

        System.out.println("end2 ---");


        System.out.println("l---" + r);
//        Thread.sleep(2000);

    }


    static class Demo3 {

        private static void day2() {
            System.out.println(System.identityHashCode(Demo3.class));
        }

        private void day() {
            System.out.println(System.identityHashCode(this));
        }
    }
}

class Handler implements Callable<String> {

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        Thread.sleep(10000);
        System.out.println("call---");
        return "l---";
    }
}