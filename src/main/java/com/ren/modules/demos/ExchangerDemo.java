package com.ren.modules.demos;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by dongao on 2018/11/13.
 */
public class ExchangerDemo {
    private static Exchanger exchanger = new Exchanger();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String getgirlsetBoy = (String)exchanger.exchange("我其实暗恋你很久了......");
                    System.out.println("女孩说："+getgirlsetBoy);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("女生慢慢的从教室你走出来......");
                    TimeUnit.SECONDS.sleep(3);
                    //男生对女生说的话
                    String getboysetGirl = (String)exchanger.exchange("我也很喜欢你......");
                    System.out.println("男孩说：" + getboysetGirl);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
