/**
 * 
 */
package com.wpm.prometheus.util;

import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pengmingwang
 *
 */
public class SeqTest {
    public static void main(String[] args) {
        long startTimes = System.currentTimeMillis();
        int threadCount = 1000;
        int total = 100000;
        ExecutorService poolExecutorService = Executors.newFixedThreadPool(30);

        for (int i = 0; i < total; i++) {
            poolExecutorService.execute(new Worker());
        }
        long endTimes = System.currentTimeMillis();
        System.out.println("所有线程执行完毕:" + (endTimes - startTimes));
    }

}

class Worker implements Runnable {
    private static String INTFLOGID_SEQUENCE = "SELECT csi_nextval('csi_IntfLogId_SEQ');";
    static String sql = "select * from csi_sequence;";
    int start = 0;
    int end = 0;
    String name = "";
    CountDownLatch latch;

    public Worker() {
    }

    @Override
    public void run() {
        System.out.println("线程" + Thread.currentThread().getName() + "正在执行。。");
        try {
            DbTest.select(INTFLOGID_SEQUENCE);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
