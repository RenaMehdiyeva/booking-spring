package az.turing.bookingspring.service;

import az.turing.bookingspring.dto.FlightDto;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {

    void saveFlight(FlightDto flightDto);

    List<FlightDto> findAll();

    FlightDto findById(int id);

    void deleteFlight(int id);

    void updateFlightInfo(int id, FlightDto flightDto);

    List<FlightDto> getFlightsInNext24Hours();

    List<FlightDto> searchFlights(String toCity, LocalDate date, int numberOfPassenger);

    boolean hasAvailableSeats(int flightId, int requiredSeats);
}
