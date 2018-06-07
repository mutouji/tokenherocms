package org.delphy.tokenherocms.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

public class TestBasic {
    @Test
    public void testForeach() {
        List<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");

        items.forEach(item->{
            if("C".equals(item)){
                return;
            }
            System.out.println(item);
        });
    }

    @Test
    public void testTimer() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                System.out.println(sdf.format(new Date()) + " A: do task");
            }
        }, 0, 5*1000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                System.out.println(sdf.format(new Date()) + " B: sleep");
                try {
                    Thread.sleep(20*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 10*1000, 5000);

        try {
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void printTime() {
        Long time = new Date().getTime();
        System.out.print(time);
    }
}
