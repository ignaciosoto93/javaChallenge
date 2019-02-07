package com.marb.demo.config;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.marb.demo.module.delayed.domain.job.DelayedCallJob;

@Configuration
public class SchedulerConfig {

	@Value("${quartz.scheduler.delayed-call-processor.interval-in-seconds}")
	private Integer delayedCallIntervalInSeconds;

	@Bean
	public JobDetail delayedCallJob() {
		return JobBuilder.newJob(DelayedCallJob.class)
				.withIdentity("delayed-call-processor", "delayed-call")
				.withDescription("Delayed Call processor")
				.usingJobData(new JobDataMap())
				.storeDurably()
				.build();
	}

	@Bean
	public Trigger delayedCallTrigger() {

		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(delayedCallIntervalInSeconds).repeatForever();

		return TriggerBuilder.newTrigger()
				.forJob(delayedCallJob())
				.withIdentity(delayedCallJob().getKey().getName(), "delayed-call-triggers")
				.withDescription("Delayed Call processor Trigger")
				.withSchedule(scheduleBuilder)
				.build();
	}
}
