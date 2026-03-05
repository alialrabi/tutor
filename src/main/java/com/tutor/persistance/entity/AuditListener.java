package com.tutor.persistance.entity;

import com.tutor.security.SecurityUtil;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@EntityListeners(AuditListener.class)
public class AuditListener {
    @PrePersist
    public void setCreatedOn(Object entity) {
        if (entity instanceof AuditableEntity auditable) {
            LocalDateTime now = LocalDateTime.now();
            auditable.setCreatedAt(now);
            auditable.setUpdatedAt(now);

            Long userId = SecurityUtil.getCurrentUserId();

            auditable.setCreatedBy(userId);
            auditable.setUpdatedBy(userId);
        }
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        if (entity instanceof AuditableEntity auditable) {
            LocalDateTime now = LocalDateTime.now();
            auditable.setUpdatedAt(now);
            Long userId = SecurityUtil.getCurrentUserId();
            auditable.setUpdatedBy(userId);
        }

        }
}
