package com.tutor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tutors")
public class Tutor extends BaseEntity {
    private String name;
    private String subject;
    private String email;
}
