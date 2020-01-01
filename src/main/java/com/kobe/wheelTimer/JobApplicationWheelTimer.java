package com.kobe.wheelTimer;

import java.util.concurrent.TimeUnit;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;

public class JobApplicationWheelTimer {
	
	public static void main(String[] args) throws InterruptedException {
		Timer timer = new HashedWheelTimer();
		MyTimerTask myTask = new MyTimerTask(false);
		timer.newTimeout(myTask, 5, TimeUnit.SECONDS);
		
		long start = System.currentTimeMillis();
		while(!myTask.isCompleted){
			Thread.sleep(1000);
			System.out.println("waiting: " + (System.currentTimeMillis()-start) + "ms");
		}
	}

}
