package com.marb.demo.module.delayed.processor;

import com.marb.demo.module.delayed.domain.model.DelayedCall;

/**
 * Boiler plate code to actually execute a {@link DelayedCall}
 *
 * @author anbernas
 */
public interface DelayedIntegrationProcessorManager {

	/**
	 * Processes a single call, identifying what to do in terms
	 * of retries, error management, etc.
	 *
	 * @param call
	 */
	public void processCall(DelayedCall call);

}
