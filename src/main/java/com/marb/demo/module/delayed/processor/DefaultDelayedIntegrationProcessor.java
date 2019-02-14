package com.marb.demo.module.delayed.processor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marb.demo.module.delayed.domain.api.dto.DelayedCallResponseDto;
import com.marb.demo.module.delayed.domain.model.DelayedCall;
import com.marb.demo.module.delayed.domain.model.DelayedCallExamplePayload;
import com.newrelic.api.agent.NewRelic;

@Component
public class DefaultDelayedIntegrationProcessor implements DelayedIntegrationProcessor{

	private static final Log log = LogFactory.getLog(DefaultDelayedIntegrationProcessor.class);
	private final ObjectMapper objectMapper;

	public DefaultDelayedIntegrationProcessor(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public boolean applies(DelayedCall call) {
		return true;
	}

	@Override
	public DelayedCallResponseDto process(DelayedCall call) {
		log.info(String.format("Default processor processing delayed call [%s]",call));
		String payload = call.getPayload();
		try {
			DelayedCallExamplePayload delayedCallExamplePayload = objectMapper
					.readValue(payload, DelayedCallExamplePayload.class);
			log.info(String.format("Payload received [%s]",delayedCallExamplePayload));
		} catch (IOException e) {
			log.error(e.getMessage(),e);
			Map<String, String> params = new HashMap<>();
			params.put("delayCallId", String.valueOf(call.getId()));
			NewRelic.noticeError(e, params);
			throw new RuntimeException(e);
		}
		return null;
	}
}
