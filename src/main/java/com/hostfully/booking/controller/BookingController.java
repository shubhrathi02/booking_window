package com.hostfully.booking.controller;

import com.hostfully.booking.dto.BookingDto;
import com.hostfully.booking.exception.BadRequestException;
import com.hostfully.booking.resource.BookingResource;
import com.hostfully.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(method = RequestMethod.POST, path = "/bookings")
    public void createBooking(@RequestBody BookingDto bookingDto, @RequestParam Long userId) {
        bookingService.createBooking(bookingDto, userId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/bookings")
    public List<BookingResource> getBookings(@RequestParam Long userId) {
        return bookingService.getBookingsByUser(userId);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/bookings")
    public void deleteBooking(@RequestBody Long id, @RequestParam Long userId) {
        if (userId == null) {
            throw new RuntimeException("UserId not available");
        }
        bookingService.deleteBooking(id, userId);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/bookings/{id}")
    public void updateBooking(@RequestBody BookingDto bookingDto, @PathVariable Long id, @RequestParam Long userId) {
        if (userId == null) {
            throw new RuntimeException("UserId not available");
        }
        bookingService.updateBooking(bookingDto, id, userId);
    }
}
