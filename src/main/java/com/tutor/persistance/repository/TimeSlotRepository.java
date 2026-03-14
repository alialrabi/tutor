package com.tutor.persistance.repository;

import com.tutor.persistance.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long>, JpaSpecificationExecutor<TimeSlot> {
    void deleteByTutorId(Long tutorId);
    List<TimeSlot> findByTutorId(Long tutorId);
}
