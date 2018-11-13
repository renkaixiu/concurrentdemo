package com.ren.modules.demos;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dongao on 2018/11/13.
 */
public class CyclicBarrierDemo {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(6, new Runnable() {
        @Override
        public void run() {
            System.out.println("所有运动员入场，裁判员一声令下！！！！！");
        }
    });

    public static void main(String[] args)  {
        System.out.println("运动员准备进场，全场欢呼............");
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i <6 ; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+"进场！！！");
                        cyclicBarrier.await();
                        System.out.println(Thread.currentThread().getName()+"出发！！！");
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });
        }
        executorService.shutdown();
    }
}
