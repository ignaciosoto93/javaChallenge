package com.marb.demo.module.delayed.processor;

import static java.util.Optional.ofNullable;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.marb.demo.module.delayed.domain.api.dto.DelayedCallResponseDto;
import com.marb.demo.module.delayed.domain.model.DelayedCall;
import com.marb.demo.module.delayed.domain.model.DelayedCallLog;
import com.marb.demo.module.delayed.domain.model.DelayedCallStatus;
import com.marb.demo.module.delayed.domain.repository.DelayedCallRepository;
import com.marb.framework.api.filter.MdcFilter;
import com.newrelic.api.agent.NewRelic;

@Component
public class DefaultDelayedIntegrationProcessorManager implements DelayedIntegrationProcessorManager {

	private static final Log log = LogFactory.getLog(DefaultDelayedIntegrationProcessorManager.class);

	/* how many times to retry a failed message */
	private static final int MAX_RETRIES = 5;

	private DelayedCallRepository delayedCallRepository;

	private List<DelayedIntegrationProcessor> processors;

	@Autowired
	public DefaultDelayedIntegrationProcessorManager(DelayedCallRepository delayedCallRepository,
			List<DelayedIntegrationProcessor> processors) {
		super();
		this.delayedCallRepository = delayedCallRepository;
		this.processors = processors;
	}

	@Transactional
	@Override
	public void processCall(final DelayedCall call) {
		log.info("processing delayed call " + call);
		try{
			MDC.put(MdcFilter.UOW, UUID.randomUUID().toString());
			processors.stream().filter(processor -> processor.applies(call)).findFirst()
					.ifPresent(processor -> executeAndUpdate(processor, call));
		}finally{
			MDC.remove(MdcFilter.UOW);
		}

	}

	/**
	 * @param processor
	 * @param call
	 */
	private void executeAndUpdate(DelayedIntegrationProcessor processor, DelayedCall call) {
		log.info("using " + processor);
		DelayedCallResponseDto response = null;
		try {
			response = processor.process(call);
			if (response == null){
				throw new IllegalArgumentException("response could not be null");
			}
		} catch (Exception e) {
			log.error("unexpected exception throw by processor " + call, e);
			Map<String, String> params = new HashMap<>();
			params.put("DelayCallId", String.valueOf(call.getId()));
			NewRelic.noticeError(e, params);
			response = new DelayedCallResponseDto(DelayedCallResponseDto.ResponseStatus.RETRY,
					ofNullable(e.getMessage()).orElse(e.getClass().getCanonicalName()));
		}
		DelayedCallStatus newStatus = call.getStatus();
		log.info("execution response " + response);
		if (response.isSuccessful()) {
			log.info("success");
			newStatus = DelayedCallStatus.PROCESSED;
			onSuccessful(call);
		} else if (response.isFailedButRetry()) {
			if (call.getRetries() >= MAX_RETRIES) {
				log.info("max retries reached");
				newStatus = DelayedCallStatus.FAILED;
				onFailed(call);
			} else {
				log.info("failed but will retry");
				newStatus = DelayedCallStatus.RETRY;
				onRetry(call);
			}
		} else if (response.isHardFail()) {
			log.info("hard failure");
			newStatus = DelayedCallStatus.FAILED;
			onFailed(call);
		}

		DelayedCallLog delayedCallLog = DelayedCallLog.builder().withCreatedOn(new Date()).withDescription(response.getMessage())
				.withStatus(newStatus).build();
		DelayedCall delayedCallUpdated = DelayedCall.builder().fromDelayedCall(call).addRetryAttempt().withStatus(newStatus)
				.addDelayedCallLog(delayedCallLog).build();

		log.info("final status: " + delayedCallUpdated);

		delayedCallRepository.save(delayedCallUpdated);
	}

	private void onSuccessful(DelayedCall call) {
		//noop
	}

	private void onRetry(DelayedCall call) {
		//noop
	}

	private void onFailed(DelayedCall call) {
		//noop
	}

}
