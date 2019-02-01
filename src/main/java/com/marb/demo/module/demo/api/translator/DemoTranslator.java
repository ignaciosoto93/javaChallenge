package com.marb.demo.module.demo.api.translator;

import org.springframework.stereotype.Component;
import com.marb.demo.module.demo.api.dto.DemoDto;
import com.marb.demo.module.demo.domain.model.Demo;
import com.marb.framework.api.translator.Translator;

@Component
public class DemoTranslator implements Translator<DemoDto, Demo> {

	@Override
	public Demo dtoToObject(DemoDto dto) {
		return Demo.builder().withId(dto.getId()).withName(dto.getName()).build();
	}

	@Override
	public DemoDto objectToDto(Demo demo) {
		DemoDto demoDto = new DemoDto();
		demoDto.setId(demo.getId());
		demoDto.setName(demo.getName());

		return demoDto;
	}
}
