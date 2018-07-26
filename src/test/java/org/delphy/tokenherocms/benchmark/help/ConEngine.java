package org.delphy.tokenherocms.benchmark.help;

import java.net.URLConnection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConEngine {
    static int count = 0;
    //总访问量是client_num，并发量是thread_num
    int thread_num = 10;
    int client_num = 1000;

    float avg_exec_time = 0;
    float sum_exec_time = 0;
    long first_exec_time = Long.MAX_VALUE;
    long last_done_time = Long.MIN_VALUE;
    float total_exec_time = 0;

    String url = "";
    String postData = "";

    public ConEngine(int thread_num, int client_num, String url, String postData) {
        this.thread_num = thread_num;
        this.client_num = client_num;
        this.url = url;
        this.postData = postData;
    }

    public void run() {
        final  ConEngine conEngine = this;

        final ConcurrentHashMap<Integer, ThreadRecord> records = new ConcurrentHashMap<>();

        ExecutorService exec = Executors.newFixedThreadPool(thread_num);

        final CountDownLatch doneSignal = new CountDownLatch(client_num);

        for (int i = 0; i < client_num; i++) {
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    int index = getIndex();
                    long st = System.currentTimeMillis();

                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    records.put(in)
                }
            };
        }
    }

    public static  int getIndex() {
        return ++count;
    }
}
