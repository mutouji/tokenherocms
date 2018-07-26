package org.delphy.tokenherocms.benchmark.example;

import com.alibaba.fastjson.JSONObject;
import org.delphy.tokenherocms.util.HttpClientUtil;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadProxyHubApiTest {

    static int count = 0;
    //总访问量是client_num，并发量是thread_num
    int thread_num = 1000;
    int client_num = 10000;

    float avg_exec_time = 0;
    float sum_exec_time = 0;
    long first_exec_time = Long.MAX_VALUE;
    long last_done_time = Long.MIN_VALUE;
    float total_exec_time = 0;

    String url = "";
    String postData = "";

    public MultiThreadProxyHubApiTest(int thread_num, int client_num, String url, String postData){

        this.thread_num = thread_num;
        this.client_num = client_num;
        this.url = url;
        this.postData = postData;
    }


    public void run() {

        final MultiThreadProxyHubApiTest currentObj = this;

        final ConcurrentHashMap<Integer, ThreadRecord> records = new ConcurrentHashMap<Integer, ThreadRecord>();

        // 建立ExecutorService线程池
        ExecutorService exec = Executors.newFixedThreadPool(thread_num);
        // thread_num个线程可以同时访问
        // 模拟client_num个客户端访问

        final CountDownLatch doneSignal = new CountDownLatch(client_num);

        for (int i = 0; i < client_num; i++) {

            Runnable run = new Runnable() {

                public void run() {

                    int index = getIndex();
                    long st = System.currentTimeMillis();

                    try {
                        String str = HttpClientUtil.doGet("http://192.168.1.113:3300/" + index);
                        JSONObject obj = JSONObject.parseObject(str);
//                        System.out.println(str);
//                        System.out.println("index=" + index);
                        System.out.println(obj.get("msg") + "," + index);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    records.put(index, new ThreadRecord(st, System.currentTimeMillis()));
                    doneSignal.countDown();//每调用一次countDown()方法，计数器减1
                }
            };
            exec.execute(run);
        }

        try {
            //计数器大于0 时，await()方法会阻塞程序继续执行
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * 获取每个线程的开始时间和结束时间
         */
        for(int i : records.keySet()){
            ThreadRecord r = records.get(i);
            sum_exec_time += ((double)(r.et - r.st))/1000;

            if(r.st < first_exec_time){
                first_exec_time = r.st;
            }
            if(r.et > last_done_time){
                this.last_done_time = r.et;
            }
        }

        this.avg_exec_time = this.sum_exec_time / records.size();
        this.total_exec_time = ((float)(this.last_done_time - this.first_exec_time)) / 1000;


        System.out.println("======================================================");
        System.out.println("Thread Num: " + thread_num + ", Client Count: "+ client_num +".");
        System.out.println("Avg Exec Time:   " + this.avg_exec_time + " s");
        System.out.println("Total Exec Time: " + this.total_exec_time + " s");
        System.out.println("Throughput:      " + this.client_num /this.total_exec_time+ " /s");
    }

    public static synchronized int getIndex(){
        return ++count;
    }

    public static void main(String[] args) {
        //总访问量和并发量两重循环，依次增大访问
//        for(int j=500; j<600; j+= 100){
//            for(int i=10; i<100; i+=10){
//                //要测试的URL
//                String url="http://www.baidu.com/";
//                new MultiThreadProxyHubApiTest(i, j, url, "").run();
//            }
//        }
        new MultiThreadProxyHubApiTest(100, 1000, "http://192.168.1.113:3300", "").run();
        System.out.println("finished!");
    }
}
