package com.marb.demo.module.iguanafix.domain.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.marb.framework.api.exception.IfixException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JobServiceTest {

	@Autowired
	private JobService jobService;

	@Test
	public void findJobById(){
		Assertions.assertThrows(IfixException.class, () -> {
			jobService.findJobById(999L);
		});
	}
}