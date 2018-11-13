package com.ren.modules.demos;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dongao on 2018/11/13.
 */
public class ConditionDemo {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition full = lock.newCondition();
    private static Condition empty = lock.newCondition();
    private static List<Integer> list = new LinkedList<Integer>();

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 5; i++) {
            service.submit(new Productor(list, 8, lock));
        }
        for (int i = 0; i < 10; i++) {
            service.submit(new Consumer(list, lock));
        }
    }

    static class Productor implements Runnable{
        private List<Integer> list;
        private int maxLength;
        private ReentrantLock lock;
        public Productor(List list,int maxLength,ReentrantLock lock){
            this.list = list;
            this.maxLength = maxLength;
            this.lock = lock;
        }
        @Override
        public void run(){
            while (true){
                lock.lock();
                try {
                    while (list.size() == maxLength){
                        System.out.println("生产者" + Thread.currentThread().getName() + "  list以达到最大容量，进行wait");
                        full.await();
                        System.out.println("生产者" + Thread.currentThread().getName() + "  退出wait");
                    }
                    Random random = new Random();
                    int i = random.nextInt();
                    System.out.println("生产者" + Thread.currentThread().getName() + "  生产数据 "+i);
                    list.add(i);
                    empty.signalAll();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }
    static class Consumer implements Runnable{
        private List<Integer> list;
        private ReentrantLock lock;
        public Consumer(List list,ReentrantLock lock){
            this.list = list;
            this.lock = lock;
        }
        @Override
        public void run(){
            while (true){
                try {
                    lock.lock();
                    while (list.isEmpty()){
                        System.out.println("消费者" + Thread.currentThread().getName() + "  list为空，进行wait");
                        empty.await();
                        System.out.println("消费者" + Thread.currentThread().getName() + "  退出wait");
                    }
                    int i = list.remove(0);
                    System.out.println("消费者" + Thread.currentThread().getName() + "  消费数据");
                    full.signalAll();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}
