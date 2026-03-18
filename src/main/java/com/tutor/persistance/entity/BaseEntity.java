package com.tutor.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity extends AuditableEntity implements Serializable {

    @Column(name = "status")
    private Long status;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
