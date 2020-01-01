package com.kobe.redis;

public class ConcurrentJobProduction implements Runnable {

	@Override
	public void run() {
		new JobApplicationRedis().produceDelayMessage();
	}

}
