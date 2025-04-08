package com.internship.passenger_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "rate")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Byte value;
    @Column(nullable = false)
    private LocalDate createdAt;
    @Column(nullable = false)
    private Long authorId;
    @ManyToOne
    @JoinColumn(name = "passenger_profile_id")
    private PassengerProfile passenger;
}
