package com.ren.modules.demos;

/**
 * Created by dongao on 2018/11/13.
 */
public class EarlyNotifyDemo {
    private static String lockObject = "";
    private static boolean isWait = true;

    public static void main(String[] args) {
        WaitThread waitThread = new WaitThread(lockObject);
        NotifyThread notifyThread = new NotifyThread(lockObject);
        notifyThread.start();
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        waitThread.start();
    }
    static class WaitThread extends Thread{
        private String lock = "";
        public WaitThread(String lock){
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock){
                try{
                    while (isWait){
                        System.out.println(Thread.currentThread().getName()+"进入代码块");
                        System.out.println(Thread.currentThread().getName()+"开始wait");
                        lock.wait();
                        System.out.println(Thread.currentThread().getName()+"结束wait");
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    static class NotifyThread extends Thread{
        private String lock;
        public NotifyThread(String lock){
            this.lock = lock;
        }
        @Override
        public void run(){
            synchronized (lock){
                System.out.println(Thread.currentThread().getName()+"进入代码块");
                System.out.println(Thread.currentThread().getName()+"开始notify");
                lock.notify();
                isWait = false;
                System.out.println(Thread.currentThread().getName()+"结束notify");
            }
        }
    }
}
