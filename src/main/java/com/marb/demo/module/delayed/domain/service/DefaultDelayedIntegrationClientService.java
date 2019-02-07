package com.marb.demo.module.delayed.domain.service;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.marb.demo.module.delayed.domain.api.dto.DelayedCallRequestDto;
import com.marb.demo.module.delayed.domain.model.DelayedCall;
import com.marb.demo.module.delayed.domain.model.DelayedCallStatus;
import com.marb.demo.module.delayed.domain.repository.DelayedCallRepository;

@Service
public class DefaultDelayedIntegrationClientService implements DelayedIntegrationClientService {

	private static final Log log = LogFactory.getLog(DefaultDelayedIntegrationClientService.class);

	private DelayedCallRepository delayedCallRepository;

	@Autowired
	public DefaultDelayedIntegrationClientService(DelayedCallRepository delayedCallRepository) {
		super();
		this.delayedCallRepository = delayedCallRepository;
	}

	@Transactional
	@Override
	public void createCall(DelayedCallRequestDto delayedCallRequestDto) {
		log.info("creating delayed call " + delayedCallRequestDto);
		DelayedCall call = DelayedCall.builder().fromDelayedCallRequestDto(delayedCallRequestDto).build();
		DelayedCall delayedCallSaved = delayedCallRepository.save(call);
		log.info("created with id: " + delayedCallSaved.getId());
	}

}
