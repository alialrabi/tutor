package com.tutor.persistance.repository;

import com.tutor.persistance.entity.LuLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<LuLanguage, Long>, JpaSpecificationExecutor<LuLanguage> {
}
