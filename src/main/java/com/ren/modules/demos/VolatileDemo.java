package com.ren.modules.demos;

/**
 * Created by dongao on 2018/11/6.
 */
public class VolatileDemo {
    public static volatile   boolean isOver = false;
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (!isOver){
                    i++;
                    System.out.println("loop of "+i);
                }
            }
        });
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isOver = true;
    }
    }
