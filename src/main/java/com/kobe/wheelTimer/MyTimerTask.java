package com.kobe.wheelTimer;

import io.netty.util.Timeout;
import io.netty.util.TimerTask;

public class MyTimerTask implements TimerTask{
	boolean isCompleted;
	
	public MyTimerTask(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	@Override
	public void run(Timeout timeout) throws Exception {
		System.out.println("MyTimerTask is doing...");
		isCompleted = true;
	}

}
