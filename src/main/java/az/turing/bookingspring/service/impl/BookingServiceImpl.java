package az.turing.bookingspring.service.impl;

import az.turing.bookingspring.dto.BookingDto;
import az.turing.bookingspring.entity.Booking;
import az.turing.bookingspring.entity.Flight;
import az.turing.bookingspring.exceptions.BookingException;
import az.turing.bookingspring.mapper.BookingMapper;
import az.turing.bookingspring.repository.BookingRepository;
import az.turing.bookingspring.repository.FlightRepository;
import az.turing.bookingspring.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, FlightRepository flightRepository,
                              BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        Flight flight = flightRepository.findById(bookingDto.getFlight().getId())
                .orElseThrow(() -> new BookingException("Flight not found"));

        int totalTickets = bookingDto.getPassengers().size();
        if (flight.getAvailableSeats() < totalTickets) {
            throw new BookingException("Not enough seats available");
        }

        flight.setAvailableSeats(flight.getAvailableSeats() - totalTickets);
        flightRepository.save(flight);

        Booking booking = bookingMapper.toEntity(bookingDto);
        booking.setBookingDate(LocalDateTime.now());
        booking.setNumberOfTickets(totalTickets);
        booking = bookingRepository.save(booking);

        return bookingMapper.toDto(booking);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingException("Booking not found"));
        Flight flight = booking.getFlight();
        flight.setAvailableSeats(flight.getAvailableSeats() + booking.getNumberOfTickets());
        flightRepository.save(flight);
        bookingRepository.delete(booking);
    }

    @Override
    public List<BookingDto> getBookingsByPassengerId(Long passengerId) {
        return bookingRepository.findAll().stream()
                .filter(b -> b.getPassengers().stream().anyMatch(p -> p.getId().equals(passengerId)))
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }
}
