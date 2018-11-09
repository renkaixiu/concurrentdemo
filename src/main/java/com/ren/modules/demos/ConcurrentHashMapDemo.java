package com.ren.modules.demos;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dongao on 2018/11/8.
 */
public class ConcurrentHashMapDemo {
    final static Map<String, String> map = new ConcurrentHashMap<String, String>();
    public static void main(String[] args) throws InterruptedException {
       // for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 25; i++) {
                        map.put(String.valueOf(i), String.valueOf(i));
                    }
                }
            });
            Thread t2 =new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 25; i < 50; i++) {
                        map.put(String.valueOf(i), String.valueOf(i));
                    }
                }
            });
            t1.start();
            t2.start();
        Thread.currentThread().sleep(1000);
            for (int j = 0; j < 50; j++) {
                if(!String.valueOf(j).equals(map.get(String.valueOf(j)))){
                    System.out.println(j +":"+map.get(j));
                }
            }
        }

  //  }
}
