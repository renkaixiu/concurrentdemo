package com.ren.modules.demos;

/**
 * Created by dongao on 2018/11/6.
 */
public class SynchronizedDemo extends Thread {
    private static int count = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SynchronizedDemo());
            thread.start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + count);
    }

    @Override
    public void run() {
        synchronized (SynchronizedDemo.class){
            for (int i = 0; i < 1000000; i++){
                count++;
            }
        }
    }
}
