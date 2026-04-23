package com.tutor.persistance.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "lu_languages", schema = "tutor")
public class LuLanguage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lu_languages_seq_gen")
    @SequenceGenerator(
            name = "lu_languages_seq_gen",
            sequenceName = "lu_languages_id_seq",
            schema = "tutor",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "name_ar", nullable = false)
    private String nameAr;

    @Column(name = "name_en", nullable = false)
    private String nameEn;
}
