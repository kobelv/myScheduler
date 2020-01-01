package com.kobe.quartz;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class JobApplicationQuartz {

	public static void main(String[] args) throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("myJob", "groupJob").build();
		
		Trigger	trigger = TriggerBuilder.newTrigger().withIdentity("triggerA", "groupA")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(8)
                        .withIntervalInSeconds(2)).build();
		
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
		
	}

}
