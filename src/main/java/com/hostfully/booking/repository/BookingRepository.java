package com.hostfully.booking.repository;

import com.hostfully.booking.entity.BookingEntity;
import com.hostfully.booking.enums.BookingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends CrudRepository<BookingEntity, Long>, JpaRepository<BookingEntity, Long> {

    List<BookingEntity> getAllByUserId(long userId);

    BookingEntity getByIdAndUserId(long id, long userId);


    @Query("" +
            "SELECT " +
            "CASE " +
            "           WHEN count(*) > 0 " +
            "                THEN true " +
            "           ELSE false " +
            "           END " +
            " FROM Bookings b " +
            " WHERE " +
            "       (b.bookingType = com.hostfully.booking.enums.BookingType.GENERAL " +
            "           OR (b.bookingType = com.hostfully.booking.enums.BookingType.BLOCK " +
            "               AND 'GENERAL' = ?3)" +
            "       )" +
            "       AND ((b.startDate <= ?1 AND b.endDate >= ?1) " +
            "           OR (b.startDate <= ?2 AND b.endDate >= ?2) " +
            "           OR (b.startDate >= ?1 AND b.endDate <= ?2)" +
            "       ) ")
    Boolean doesBookingDateIntersect(LocalDate startDate, LocalDate endDate, String bookingType);
}
