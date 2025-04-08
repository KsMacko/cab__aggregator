package com.internship.driver_service.entity;

import com.internship.driver_service.enums.DriverStatus;
import com.internship.driver_service.enums.FareType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Entity
@Table(name = "profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class DriverProfile {
    @Id
    private Long profileId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String carNumber;
    @Column(nullable = false)
    private String carDescription;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FareType fareType;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DriverStatus driverStatus;
    @Column(nullable = false, unique = true)
    private String phone;

    @OneToOne
    @MapsId
    @JoinColumn(name = "profileId")
    private DriverAccount driverAccount;
    @OneToMany(mappedBy = "driver")
    private List<Rate> rates;


}
