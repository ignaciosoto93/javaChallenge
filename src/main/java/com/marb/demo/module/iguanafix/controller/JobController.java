package com.marb.demo.module.iguanafix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.marb.demo.module.iguanafix.api.dto.JobDto;
import com.marb.demo.module.iguanafix.api.service.JobServiceApi;

@RestController
@RequestMapping(path = "/job")
public class JobController {
	private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	private JobServiceApi jobServiceApi;

	@GetMapping("/{jobId}")
	@ResponseBody
	public JobDto getDemo(@PathVariable long jobId) {
		logger.info(String.format("get job with id: %d", jobId));
		JobDto job = jobServiceApi.getJobById(jobId);
		logger.info(String.format("Job found: %s", job));
		return job;
	}

}
