package com.marb.demo.module.demo.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.marb.demo.module.demo.api.dto.DemoDto;
import com.marb.demo.module.demo.api.service.DemoServiceApi;

@RestController
@RequestMapping(path = "/demo")
public class DemoController {
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	private DemoServiceApi demoServiceApi;

	@GetMapping
	@ResponseBody
	public List<DemoDto> getAllDemos() {
		logger.info("get demos");
		List<DemoDto> demos = demoServiceApi.getDemos();
		logger.info(String.format("%d demos found", demos.size()));
		return demos;
	}

	@PostMapping
	@ResponseBody
	public DemoDto createDemo(@RequestBody DemoDto demoDto) {
		logger.info(String.format("Create demo: %s", demoDto));
		DemoDto demo = demoServiceApi.createDemo(demoDto);
		logger.info(String.format("Demo created : %s", demoDto));
		return demo;
	}

	@GetMapping("/{demoId}")
	@ResponseBody
	public DemoDto getDemo(@PathVariable long demoId) {
		logger.info(String.format("get demo with id: %d", demoId));
		DemoDto demo = demoServiceApi.getDemoByID(demoId);
		logger.info(String.format("Demo found: %s", demo));
		return demo;
	}

	@PutMapping("/{demoId}")
	@ResponseBody
	public DemoDto updateDemo(@PathVariable long demoId, @RequestBody DemoDto demoDto) {
		logger.info(String.format("Update demo with id: %d", demoId));
		demoDto.setId(demoId);
		DemoDto updatedDemo = demoServiceApi.updateDemo(demoDto);
		logger.info(String.format("Updated demo: %s", updatedDemo));
		return updatedDemo;
	}

	@DeleteMapping("/{demoId}")
	public void deleteDemo(@PathVariable long demoId) {
		logger.info(String.format("delete demo with id: %d", demoId));
		demoServiceApi.deleteDemo(demoId);
	}
}
