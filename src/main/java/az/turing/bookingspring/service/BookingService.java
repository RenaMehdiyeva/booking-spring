package az.turing.bookingspring.service;

import az.turing.bookingspring.dto.BookingDto;

import java.util.List;

public interface BookingService {
    BookingDto createBooking(BookingDto bookingDto);
    void cancelBooking(Long bookingId);
    List<BookingDto> getBookingsByPassengerId(Long passengerId);
}
