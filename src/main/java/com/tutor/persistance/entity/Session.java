package com.tutor.persistance.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "session", schema = "tutor")
public class Session extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session_seq_gen")
    @SequenceGenerator(
            name = "session_seq_gen",
            sequenceName = "session_id_seq",
            schema = "tutor",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id", nullable = false, insertable = false, updatable = false)
    private UserProfile userProfile;

    @Column(name = "user_profile_id")
    private Long userProfileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false, insertable = false, updatable = false)
    private Tutor tutor;

    @Column(name = "tutor_id")
    private Long tutorId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_slot_id", nullable = false, insertable = false, updatable = false)
    private TimeSlot timeSlot;

    @Column(name = "time_slot_id")
    private Long timeSlotId;

}
