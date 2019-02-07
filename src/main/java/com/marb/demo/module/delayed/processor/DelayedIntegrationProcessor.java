package com.marb.demo.module.delayed.processor;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.marb.demo.module.delayed.domain.api.dto.DelayedCallResponseDto;
import com.marb.demo.module.delayed.domain.model.DelayedCall;

/**
 * Custom external-service integrations need to implement this service
 * to actually be performed.
 *
 * @author anbernas
 */
public interface DelayedIntegrationProcessor {

	/**
	 * Can this Call be applied to this processor?
	 *
	 * @param call
	 * @return
	 */
	boolean applies(DelayedCall call);

	/**
	 * If can be applied, then execute whatever is needed.
	 * This method is expected to NOT THROW any exceptions
	 *
	 * @param call
	 * @return true if successfully executed and needs not to run again
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	DelayedCallResponseDto process(DelayedCall call);

}
