package com.marb.demo.module.iguanafix.api.translator;

import org.springframework.stereotype.Component;
import com.marb.demo.module.iguanafix.api.dto.JobDto;
import com.marb.demo.module.iguanafix.domain.model.Job;
import com.marb.framework.api.translator.Translator;

@Component
public class JobTranslator implements Translator<JobDto, Job> {

	@Override
	public Job dtoToObject(JobDto dto) {
		return Job.builder().withId(dto.getId()).withTitle(dto.getTitle()).build();
	}

	@Override
	public JobDto objectToDto(Job job) {
		JobDto jobDto = new JobDto();
		jobDto.setId(job.getId());
		jobDto.setTitle(job.getTitle());

		return jobDto;
	}
}
