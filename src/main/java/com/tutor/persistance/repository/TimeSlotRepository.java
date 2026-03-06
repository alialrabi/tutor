package com.tutor.persistance.repository;

import com.tutor.persistance.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long>, JpaSpecificationExecutor<TimeSlot> {
}
