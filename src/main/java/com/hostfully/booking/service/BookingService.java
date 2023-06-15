package com.hostfully.booking.service;

import com.hostfully.booking.dto.BookingDto;
import com.hostfully.booking.entity.BookingEntity;
import com.hostfully.booking.enums.BookingType;
import com.hostfully.booking.exception.BadRequestException;
import com.hostfully.booking.repository.BookingRepository;
import com.hostfully.booking.resource.BookingResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BookingEntity createBooking(BookingDto bookingDto, Long userId) {

        if (userId == null) {
            throw new BadRequestException("UserId not available");
        }
        validateBooking(bookingDto);

        BookingEntity booking = new BookingEntity();
        booking.setUserId(userId);
        booking.setStartDate(bookingDto.getStartDate());
        booking.setEndDate(bookingDto.getEndDate());
        booking.setBookingType(bookingDto.getBookingType());

        return bookingRepository.save(booking);

    }

    private void validateBooking(BookingDto bookingDto) {
        boolean intersectionExists = bookingRepository.doesBookingDateIntersect(bookingDto.getStartDate(),
                    bookingDto.getEndDate(), bookingDto.getBookingType().toString());
            if (intersectionExists)
                throw new BadRequestException("Invalid start date");
    }

    public List<BookingResource> getBookingsByUser(Long userId) {
        if (userId == null) {
            throw new BadRequestException("UserId not available");
        }

        List<BookingEntity> bookingEntities = bookingRepository.getAllByUserId(userId);

        List<BookingResource> bookings = new ArrayList<>();
        bookingEntities.forEach(bookingEntity -> bookings.add(modelMapper.map(bookingEntity, BookingResource.class)));

        return bookings;
    }

    public void deleteBooking(Long id, Long userId) {
        if (userId == null || id == null) {
            throw new BadRequestException("Invalid Delete Request");
        }

        BookingEntity bookingEntity = bookingRepository.getByIdAndUserId(id, userId);
        if(bookingEntity == null) {
            throw new RuntimeException("Bad request: Booking does not exist.");
        }
        bookingRepository.delete(bookingEntity);
        bookingRepository.flush();
    }

    public void updateBooking(BookingDto bookingDto, Long id, Long userId) {
        if (userId == null || id == null) {
            throw new BadRequestException("Invalid Delete Request");
        }

        BookingEntity bookingEntity = bookingRepository.getByIdAndUserId(id, userId);
        if(bookingEntity == null) {
            throw new RuntimeException("Bad request: Booking does not exist.");
        }

        validateBooking(bookingDto);

        bookingEntity.setStartDate(bookingDto.getStartDate());
        bookingEntity.setEndDate(bookingDto.getEndDate());

        bookingRepository.saveAndFlush(bookingEntity);
    }
}
