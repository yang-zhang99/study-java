package com.java.thread;

public class Demo {

    private Demo() {
    }

    private void year() {
        Demo2 demo2 = new Demo2();
        demo2.month();
        System.out.println("Demo2");

    }

    class Demo2 {

        private void month() {
            Demo demo = new Demo();
            demo.year();

            System.out.println("Demo2");
        }
    }

}
