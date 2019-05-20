package com.yahoo.ycsb.workloads;

import com.yahoo.ycsb.generator.*;
import com.yahoo.ycsb.generator.UniformLongGenerator;

public class CoreWorkload {

	protected NumberGenerator keychooser;

	protected long recordcount;
	
	public void init() {
	    recordcount = 1000;

		keychooser = new UniformLongGenerator(0, recordcount - 1);
	}

	long nextKeynum() {
		return keychooser.nextValue().intValue();
	}
}