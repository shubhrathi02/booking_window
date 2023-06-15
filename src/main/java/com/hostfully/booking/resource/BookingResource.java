package com.hostfully.booking.resource;

import com.hostfully.booking.enums.BookingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingResource {

    private long id;
    private long userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BookingType bookingType;
}
