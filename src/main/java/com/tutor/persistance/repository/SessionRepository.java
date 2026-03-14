package com.tutor.persistance.repository;

import com.tutor.persistance.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>, JpaSpecificationExecutor<Session> {
    List<Session> findByUserProfileId(Long userProfileId);
    List<Session> findByTutorId(Long tutorId);
    List<Session> findByTutorIdAndDateBetween(Long tutorId, LocalDate startDate, LocalDate endDate);
}
