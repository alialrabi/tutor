package com.tutor.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class BaseEntity extends AuditableEntity implements Serializable {

    @Column(name = "status")
    private Integer status;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
