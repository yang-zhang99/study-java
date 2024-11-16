package com.yang.thread.chapter16;

public class EatNoodleThread extends Thread {
    private final String name;

    private final Tableware leftTool;

    private final Tableware rightTool;

    public EatNoodleThread(String name, Tableware leftTool, Tableware rightTool) {
        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    @Override
    public void run() {
        while (true) {
            this.eat();
        }
    }

    private void eat() {
        synchronized (leftTool) {
            System.out.println(name + " take up" + leftTool + "(left)");

            synchronized (rightTool) {
                System.out.println(name + " take up" + rightTool + "(right");
                System.out.println(name + " is eating now .");
                System.out.println(name + " put down" + rightTool + "(right)");
            }
            System.out.println(name + " put down" + leftTool + "(left)");
        }
    }

    public static void main(String[] args) {
        Tableware leftTool = new Tableware("leftTool");
        Tableware rightTool = new Tableware("rightTool");
        new EatNoodleThread("A", leftTool, rightTool).start();
        new EatNoodleThread("B", leftTool, rightTool).start();
    }
}
