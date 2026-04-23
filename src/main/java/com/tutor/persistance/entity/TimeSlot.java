package com.tutor.persistance.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "time_slot", schema = "tutor")
public class TimeSlot extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "time_slot_seq_gen")
    @SequenceGenerator(
            name = "time_slot_seq_gen",
            sequenceName = "time_slot_id_seq",
            schema = "tutor",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false, insertable = false, updatable = false)
    private Tutor tutor;

    @Column(name = "tutor_id")
    private Long tutorId;

    @Column(columnDefinition = "time")
    private LocalTime startTime;

    @Column(columnDefinition = "time")
    private LocalTime endTime;

    @Column(name = "day_of_week")
    private Integer dayOfWeek;

}
