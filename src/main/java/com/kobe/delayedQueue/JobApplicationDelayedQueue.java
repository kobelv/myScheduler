package com.kobe.delayedQueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class JobApplicationDelayedQueue {

	public static void main(String[] args) throws InterruptedException {
		DelayQueue<MyDelayedJob> queue = new DelayQueue<>();
		//queue.put(new MyDelayedJob(TimeUnit.NANOSECONDS.convert(3, TimeUnit.SECONDS), "job1"));
		queue.put(new MyDelayedJob(TimeUnit.NANOSECONDS.convert(5, TimeUnit.SECONDS), "job2"));
		//queue.put(new MyDelayedJob(TimeUnit.NANOSECONDS.convert(6, TimeUnit.SECONDS), "job3"));
		
		long start = System.currentTimeMillis();  
		while(!queue.isEmpty()){
			queue.take().doJob();
			System.out.println("interval: " + (System.currentTimeMillis()-start) + "ms");
			
		}
		
	}

}
