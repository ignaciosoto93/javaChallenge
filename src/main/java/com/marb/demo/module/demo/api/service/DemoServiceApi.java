package com.marb.demo.module.demo.api.service;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.marb.demo.module.demo.api.dto.DemoDto;
import com.marb.demo.module.demo.api.translator.DemoTranslator;
import com.marb.demo.module.demo.domain.model.Demo;
import com.marb.demo.module.demo.domain.service.DemoService;

@Service
public class DemoServiceApi {
	private static final Logger logger = LoggerFactory.getLogger(DemoServiceApi.class);

	@Autowired
	private DemoService demoService;
	@Autowired
	private DemoTranslator demoTranslator;

	public List<DemoDto> getDemos() {
		return demoService.findAllDemos().stream().map(demoTranslator::objectToDto).collect(Collectors.toList());
	}

	public DemoDto getDemoByID(long demoId) {
		Demo demo = demoService.findDemoById(demoId);
		return demoTranslator.objectToDto(demo);
	}

	public DemoDto updateDemo(DemoDto demoDto) {
		Demo demo = demoTranslator.dtoToObject(demoDto);
		Demo updatedDemo = demoService.updateDemo(demo);
		return demoTranslator.objectToDto(updatedDemo);
	}

	public DemoDto createDemo(DemoDto demoDto) {
		Demo demo = demoTranslator.dtoToObject(demoDto);
		Demo updatedDemo = demoService.saveDemo(demo);
		return demoTranslator.objectToDto(updatedDemo);
	}

	public void deleteDemo(long demoId) {
		demoService.delete(demoId);
	}
}
