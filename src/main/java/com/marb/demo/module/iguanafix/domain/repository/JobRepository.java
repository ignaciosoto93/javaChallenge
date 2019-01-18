package com.marb.demo.module.iguanafix.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.marb.demo.module.iguanafix.domain.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
