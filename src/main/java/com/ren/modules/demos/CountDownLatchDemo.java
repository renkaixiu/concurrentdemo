package com.ren.modules.demos;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dongao on 2018/11/12.
 */
public class CountDownLatchDemo {
    private static CountDownLatch startSignal = new CountDownLatch(1);
    private static CountDownLatch endSignal = new CountDownLatch(6);
    private static CountDownLatch readySignal = new CountDownLatch(6);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 6; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + " 运动员等待裁判员响哨！！！");
                        readySignal.countDown();
                        startSignal.await();
                        System.out.println(Thread.currentThread().getName() + "正在全力冲刺");
                        endSignal.countDown();
                        System.out.println(Thread.currentThread().getName() + "  到达终点");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        //Thread.sleep(1000);
        readySignal.await();
        System.out.println("裁判员发号施令啦！！！");
        startSignal.countDown();
        endSignal.await();
        System.out.println("所有运动员到达终点，比赛结束！");
        executorService.shutdown();
    }
}
