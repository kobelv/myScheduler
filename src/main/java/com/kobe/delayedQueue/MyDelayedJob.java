package com.kobe.delayedQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MyDelayedJob implements Delayed {
	private long timeOut;
	private String jobName;
	
	public MyDelayedJob(long timeOut, String jobName) {
		this.timeOut = timeOut + System.nanoTime();
		this.jobName = jobName;
	}

	@Override
	public int compareTo(Delayed o) {
		if(this == o){
			return 0;
		}
		long diff = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
		
		return diff == 0 ? 0 : (diff>0?1:-1);
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(timeOut-System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	public void doJob(){
		System.out.println("job-" + jobName + " is doing...");
	}
	
}
