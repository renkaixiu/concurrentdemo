package com.ren.modules.demos;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by dongao on 2018/11/9.
 */
public class ConcurrentLinkedQueueDemo {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
      /*  queue.offer(1);
        queue.offer(2);
        queue.poll();*/
        Thread thread1 = new Thread(() -> {
            Integer value = queue.poll();
           /* try {
                Thread.currentThread().sleep(1000);
            }catch (Exception e){

            }*/
            System.out.println(Thread.currentThread().getName() + " poll 的值为：" + value);
            System.out.println("queue当前是否为空队列：" + queue.isEmpty());
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            queue.offer(1);
        });
        thread2.start();
    }
}
