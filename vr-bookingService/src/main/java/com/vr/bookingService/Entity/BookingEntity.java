package com.vr.bookingService.Entity;

import javax.persistence.*;

@Entity
@Table(name = "bookings")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(nullable = false)
    private String bookingCurrency;
}
