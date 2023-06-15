package com.hostfully.booking.entity;

import com.hostfully.booking.enums.BookingType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Bookings")
@Getter
@Setter
@ToString
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private BookingType bookingType;

    private Long userId;

}
