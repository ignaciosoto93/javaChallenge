package com.marb.demo.module.demo.domain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.marb.demo.module.demo.domain.model.Demo;
import com.marb.demo.module.demo.domain.repository.DemoRepository;
import com.marb.framework.api.exception.IfixError;
import com.marb.framework.api.exception.IfixException;

@Service
public class DemoService {

	@Autowired
	private DemoRepository demoRepository;

	public Demo findDemoById(Long id) {
		return demoRepository.findById(id).orElseThrow(
				() -> new IfixException(IfixError.NO_SUCH_ELEMENT_FOUND, "There is no demo with id: " + id));
	}

	public List<Demo> findAllDemos() {
		return demoRepository.findAll();
	}

	public Demo saveDemo(Demo demo) {
		return demoRepository.save(demo);
	}

	public Demo updateDemo(Demo demo) {
		return demoRepository.save(demo);
	}

	public void delete(long demoId) {
		demoRepository.deleteById(demoId);
	}
}
