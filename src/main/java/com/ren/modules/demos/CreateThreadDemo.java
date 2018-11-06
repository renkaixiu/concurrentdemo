package com.ren.modules.demos;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by dongao on 2018/11/6.
 */
public class CreateThreadDemo {
    public static void main(String[] args) {
        Thread thread1 = new Thread(){
            @Override
            public void run(){
                System.out.println("继承重写run()创建线程");
            }
        };
        Thread thread2 = new Thread(new Runnable() {
                public void run() {
                    System.out.println("Runnable创建线程");
                }
        });
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new Callable<String>() {
            public String call() throws Exception {
                return "callable创建线程";
            }
        });
        try {
            String result = future.get();
        }catch (Exception e){
            e.printStackTrace();
        }
        thread1.start();
        thread2.start();
    }
}
