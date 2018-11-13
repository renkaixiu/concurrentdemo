package com.ren.modules.demos;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by dongao on 2018/11/9.
 */
public class BlockingQueueDemo {
    private static BlockingQueue blockingQueue = new LinkedBlockingQueue();

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 5; i++) {
            service.execute(new Productor(blockingQueue));
        }
        for (int i = 0; i < 10; i++) {
            service.execute(new Consumer(blockingQueue));
        }
        service.shutdown();
    }
    static class Productor implements Runnable {
        private BlockingQueue queue;
        public Productor(BlockingQueue queue){
            this.queue = queue;
        }
        @Override
        public void run(){
            try {
                while (true) {
                    Random random = new Random();
                    int i = random.nextInt();
                    System.out.println("生产者" + Thread.currentThread().getName() + "生产数据" + i);
                    queue.put(i);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    static class Consumer implements Runnable {
        private BlockingQueue queue;

        public Consumer(BlockingQueue queue) {
            this.queue = queue;
        }
        @Override
        public void run(){
            try {
                while (true){
                    int i = (Integer)queue.take();
                    System.out.println("消费者" + Thread.currentThread().getName() + "消费数据" + i);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
