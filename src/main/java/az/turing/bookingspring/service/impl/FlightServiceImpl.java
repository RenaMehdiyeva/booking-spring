package az.turing.bookingspring.service.impl;

import az.turing.bookingspring.dto.FlightDto;
import az.turing.bookingspring.entity.Flight;
import az.turing.bookingspring.mapper.FlightMapper;
import az.turing.bookingspring.repository.FlightRepository;
import az.turing.bookingspring.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Override
    public void saveFlight(FlightDto flightDto) {
        Flight flight = flightMapper.toEntity(flightDto);
        flightRepository.save(flight);
    }

    @Override
    public List<FlightDto> findAll() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FlightDto findById(int id) {
        return flightRepository.findById((long) id)
                .map(flightMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    @Override
    public void deleteFlight(int id) {
        flightRepository.deleteById((long) id);
    }

    @Override
    public void updateFlightInfo(int id, FlightDto flightDto) {
        Flight flight = flightRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        flight.setFromCity(flightDto.getFromCity());
        flight.setToCity(flightDto.getToCity());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setAvailableSeats(flightDto.getAvailableSeats());

        flightRepository.save(flight);
    }

    @Override
    public List<FlightDto> getFlightsInNext24Hours() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next24 = now.plusHours(24);

        return flightRepository.findByFromCityAndDepartureTimeBetween("Kiev", now, next24)
                .stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightDto> searchFlights(String toCity, LocalDate date, int numberOfPassenger) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        return flightRepository.findByToCityAndDepartureTimeBetween(toCity, startOfDay, endOfDay)
                .stream()
                .filter(flight -> flight.getAvailableSeats() >= numberOfPassenger)
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasAvailableSeats(int flightId, int requiredSeats) {
        Flight flight = flightRepository.findById((long) flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        return flight.getAvailableSeats() >= requiredSeats;
    }
}
