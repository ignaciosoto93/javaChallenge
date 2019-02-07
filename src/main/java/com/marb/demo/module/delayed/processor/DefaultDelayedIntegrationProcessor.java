package com.marb.demo.module.delayed.processor;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marb.demo.module.delayed.domain.api.dto.DelayedCallResponseDto;
import com.marb.demo.module.delayed.domain.model.DelayedCall;
import com.marb.demo.module.delayed.domain.model.DelayedCallExamplePayload;

@Component
public class DefaultDelayedIntegrationProcessor implements DelayedIntegrationProcessor{

	private static final Log log = LogFactory.getLog(DefaultDelayedIntegrationProcessor.class);

	@Override
	public boolean applies(DelayedCall call) {
		return true;
	}

	@Override
	public DelayedCallResponseDto process(DelayedCall call) {
		log.info(String.format("Default processor processing delayed call [%s]",call));
		ObjectMapper om = new ObjectMapper();
		String payload = call.getPayload();
		try {
			DelayedCallExamplePayload delayedCallExamplePayload = om
					.readValue(payload, DelayedCallExamplePayload.class);
			log.info(String.format("Payload received [%s]",delayedCallExamplePayload));
		} catch (IOException e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException(e);
		}
		return null;
	}
}
