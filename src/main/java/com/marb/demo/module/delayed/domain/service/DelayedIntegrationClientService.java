package com.marb.demo.module.delayed.domain.service;

import com.marb.demo.module.delayed.domain.api.dto.DelayedCallRequestDto;

/**
 * A generic interface for storing calls to external services in a delayed
 * and controllable fashion
 *
 * @author anbernas
 */
public interface DelayedIntegrationClientService {

	/**
	 * Creates a new DelayedCall
	 */
	void createCall(DelayedCallRequestDto delayedCallRequestDto);

}
