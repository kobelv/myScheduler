package com.kobe.redis;

import java.util.concurrent.CountDownLatch;

public class ConcurrentJobApplicationRedis {
	private static final int THREADNUM = 100;
	private static CountDownLatch countdown = new CountDownLatch(THREADNUM);
	
	static class Job implements Runnable{
		@Override
		public void run() {
			try{
				countdown.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			new JobApplicationRedis().consumeDelayMessage();
		}
		
	}
	public static void main(String[] args) {
		new Thread(new ConcurrentJobProduction()).start();
		for(int i=0; i<THREADNUM; i++){
			new Thread(new Job()).start();
			countdown.countDown();
		}
	}

}
