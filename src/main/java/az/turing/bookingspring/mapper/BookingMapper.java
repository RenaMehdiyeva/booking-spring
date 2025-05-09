package az.turing.bookingspring.mapper;

import az.turing.bookingspring.dto.BookingDto;
import az.turing.bookingspring.entity.Booking;
import az.turing.bookingspring.entity.Flight;
import az.turing.bookingspring.entity.Passenger;
import az.turing.bookingspring.repository.FlightRepository;
import az.turing.bookingspring.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookingMapper {
    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;

    @Autowired
    public BookingMapper(PassengerRepository passengerRepository, FlightRepository flightRepository) {
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
    }

    public BookingDto toDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .flight(flightRepository.findById(booking.getFlight().getId())
                        .map(FlightMapper::new).map(FlightMapper::toDto).orElse(null))
                .createdAt(booking.getBookingDate())
                .passengers(booking.getPassengers().stream()
                        .map(PassengerMapper::new).map(PassengerMapper::toDto)
                        .collect(Collectors.toList()))
                .numberOfTickets(booking.getNumberOfTickets())
                .build();
    }

    public Booking toEntity(BookingDto dto) {
        Booking booking = Booking.builder()
                .id(dto.getId())
                .bookingDate(dto.getCreatedAt())
                .numberOfTickets(dto.getNumberOfTickets())
                .build();
        if (dto.getPassengers() != null) {
            List<Passenger> passengers = dto.getPassengers().stream()
                    .map(PassengerMapper::new).map(PassengerMapper::toEntity)
                    .collect(Collectors.toList());
            booking.setPassengers(passengers);
        }
        if (dto.getFlight() != null) {
            Flight flight = flightRepository.findById(dto.getFlight().getId())
                    .orElseThrow(() -> new RuntimeException("Flight not found"));
            booking.setFlight(flight);
        }
        return booking;
    }
}
