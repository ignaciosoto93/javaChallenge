package com.marb.demo.module.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.marb.demo.module.demo.domain.model.Demo;

@Repository
public interface DemoRepository extends JpaRepository<Demo, Long> {

}
