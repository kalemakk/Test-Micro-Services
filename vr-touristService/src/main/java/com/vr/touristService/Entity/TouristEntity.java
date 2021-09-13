package com.vr.touristService.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tourists")
public class TouristEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long touristId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String country;
    @Column(nullable = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String telephone;
    @Column(nullable = false)
    private boolean status;
    @Column(nullable = false)
    private String createdBy;
    @Column(nullable = false)
    private LocalDateTime whenCreated;
    @Column(nullable = true)
    private String updatedBy;
    @Column(nullable = true)
    private LocalDateTime whenUpdated;
}
