package com.marb.demo.module.delayed.domain.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.marb.demo.module.delayed.domain.model.DelayedCall;
import com.marb.demo.module.delayed.domain.model.DelayedCallStatus;

public interface DelayedCallRepository extends JpaRepository<DelayedCall, Long> {

	List<DelayedCall> findByStatusIn(List<DelayedCallStatus> statuses, Pageable pageable);
}
