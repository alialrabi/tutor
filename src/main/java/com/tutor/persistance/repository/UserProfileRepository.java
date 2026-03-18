package com.tutor.persistance.repository;

import com.tutor.persistance.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> , JpaSpecificationExecutor<UserProfile> {
    @Query("select u from UserProfile u left join fetch u.tutor where u.email = :email")
    Optional<UserProfile> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("""
    select u 
        from UserProfile u 
        left join fetch u.roles r 
        left join fetch r.permissions 
        left join fetch u.tutor t 
        where u.email = :email
    """)
    Optional<UserProfile> findByEmailWithRolesAndPermissionsAndTutor(String email);

}
