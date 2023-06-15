package com.hostfully.booking.dto;

import com.hostfully.booking.enums.BookingType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BookingDto {

    @NonNull
    private LocalDate startDate;

    @NonNull
    private LocalDate endDate;

    @NonNull
    private BookingType bookingType;
}
