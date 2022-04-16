package com.project.unitech.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.unitech.enums.RegistrationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-account")
    private User user;

    @Column(name="STATUS")
    @Enumerated(EnumType.ORDINAL)
    private RegistrationStatus status;
}
