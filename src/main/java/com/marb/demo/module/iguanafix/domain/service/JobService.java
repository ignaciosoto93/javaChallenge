package com.marb.demo.module.iguanafix.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.marb.demo.module.iguanafix.domain.model.Job;
import com.marb.demo.module.iguanafix.domain.repository.JobRepository;
import com.marb.framework.api.exception.IfixError;
import com.marb.framework.api.exception.IfixException;

@Service
public class JobService {

	@Autowired
	private JobRepository jobRepository;

	public Job findJobById(Long id) {
		return jobRepository.findById(id).orElseThrow(
				() -> new IfixException(IfixError.NO_SUCH_ELEMENT_FOUND, "There is no job with id: " + id));
	}

}
