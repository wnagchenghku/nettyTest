package com.yahoo.ycsb.workloads;

import com.yahoo.ycsb.generator.*;
import com.yahoo.ycsb.generator.UniformLongGenerator;
import java.util.concurrent.ConcurrentHashMap;

public class CoreWorkload {

	protected NumberGenerator keychooser;

	protected long recordcount;
	
	public void init() {
	    recordcount = 2000;

		// keychooser = new UniformLongGenerator(0, recordcount - 1);
		keychooser = new SequentialGenerator(0, recordcount - 1);
		// keychooser = new ScrambledZipfianGenerator(0, recordcount - 1);		
	}

	long nextKeynum() {
		return keychooser.nextValue().intValue();
	}

	public void doTransactionRead(ConcurrentHashMap map) {
		long keynum = nextKeynum();
		if (map.get(keynum) == null) {
		}
	}
	
	public void doTransactionUpdate(ConcurrentHashMap map) {
		long keynum = nextKeynum();
		map.put(keynum, "");
	}
}