package com.marb.demo.module.iguanafix.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.marb.demo.module.iguanafix.api.dto.JobDto;
import com.marb.demo.module.iguanafix.api.translator.JobTranslator;
import com.marb.demo.module.iguanafix.domain.model.Job;
import com.marb.demo.module.iguanafix.domain.service.JobService;

@Service
public class JobServiceApi {
	private static final Logger logger = LoggerFactory.getLogger(JobServiceApi.class);

	@Autowired
	private JobService jobService;
	@Autowired
	private JobTranslator jobTranslator;

	public JobDto getJobById(long demoId) {
		Job job = jobService.findJobById(demoId);
		return jobTranslator.objectToDto(job);
	}

}
