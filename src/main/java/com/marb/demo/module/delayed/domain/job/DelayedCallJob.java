package com.marb.demo.module.delayed.domain.job;

import java.util.Arrays;
import java.util.List;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.quartz.QuartzJobBean;
import com.marb.demo.module.delayed.domain.model.DelayedCall;
import com.marb.demo.module.delayed.domain.model.DelayedCallStatus;
import com.marb.demo.module.delayed.domain.service.DelayedCallService;
import com.marb.demo.module.delayed.processor.DelayedIntegrationProcessorManager;

@DisallowConcurrentExecution
public class DelayedCallJob extends QuartzJobBean {
	private static final Logger logger = LoggerFactory.getLogger(DelayedCallJob.class);

	@Autowired
	private DelayedCallService delayedCallService;
	@Autowired
	private DelayedIntegrationProcessorManager delayedIntegrationProcessorManager;

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		logger.info(String.format("Executing job [%s] with context [%s]",this.getClass().getName(),jobExecutionContext));

		Pageable pageable = PageRequest.of(0, 100);
		List<DelayedCall> delayedCalls = delayedCallService
				.findByStatusIn(Arrays.asList(DelayedCallStatus.PENDING, DelayedCallStatus.RETRY), pageable);

		delayedCalls.stream().forEach(delayedIntegrationProcessorManager::processCall);
	}
}
