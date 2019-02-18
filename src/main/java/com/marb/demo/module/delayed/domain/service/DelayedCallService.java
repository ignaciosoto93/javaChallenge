package com.marb.demo.module.delayed.domain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.marb.demo.module.delayed.domain.model.DelayedCall;
import com.marb.demo.module.delayed.domain.model.DelayedCallStatus;
import com.marb.demo.module.delayed.domain.repository.DelayedCallRepository;

@Service
public class DelayedCallService {
	private DelayedCallRepository delayedCallRepository;

	@Autowired
	public DelayedCallService(DelayedCallRepository delayedCallRepository) {
		this.delayedCallRepository = delayedCallRepository;
	}

	public List<DelayedCall> findByStatusIn(List<DelayedCallStatus> statuses, Pageable pageable){
		return delayedCallRepository.findByStatusIn(statuses, pageable);
	}

	public DelayedCall save(DelayedCall delayedCall){
		return delayedCallRepository.save(delayedCall);
	}
}
