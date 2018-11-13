package com.ren.modules.demos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by dongao on 2018/11/13.
 */
public class SemaphoreDemo {
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName()+"同学准备获取笔。。。");
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName()+"同学获得笔。。。");
                        System.out.println(Thread.currentThread().getName()+"同学正在填写表格。。。");
                        TimeUnit.SECONDS.sleep(3);
                        semaphore.release();
                        System.out.println(Thread.currentThread().getName()+"同学填完表格，归还笔");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }
}
