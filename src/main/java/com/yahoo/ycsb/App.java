package com.yahoo.ycsb;

import com.yahoo.ycsb.workloads.CoreWorkload;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.Math;

public class App 
{
	private CoreWorkload workload;

    public static void main(String[] args) {
    	new App().go();
    }

    public void go() {
    	workload = new CoreWorkload();
        workload.init();
        double readPercent = 0.95;
        ConcurrentHashMap map = new ConcurrentHashMap();

        for (int i = 0; i < 1000000; i++) {
            if (Math.random() < readPercent) {
                workload.doTransactionRead(map);
            } else {
                workload.doTransactionUpdate(map);
            }
        }
    }
}
