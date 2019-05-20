package com.yahoo.ycsb;

import com.yahoo.ycsb.workloads.CoreWorkload;

public class App 
{
	private CoreWorkload workload;

    public static void main(String[] args) {
    	new App().go();
    }

    public void go() {
    	workload = new CoreWorkload();
        workload.init();
    }
}