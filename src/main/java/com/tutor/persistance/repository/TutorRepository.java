package com.tutor.persistance.repository;

import com.tutor.persistance.entity.Tutor;
import com.tutor.persistance.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long>, JpaSpecificationExecutor<Tutor> {
    Optional<Tutor> findByUserProfile(UserProfile user);
}
