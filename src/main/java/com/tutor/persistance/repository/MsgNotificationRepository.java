package com.tutor.persistance.repository;

import com.tutor.persistance.entity.Category;
import com.tutor.persistance.entity.MsgNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MsgNotificationRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<MsgNotification> {
}
