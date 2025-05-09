package az.turing.bookingspring.mapper;

import az.turing.bookingspring.dto.FlightDto;
import az.turing.bookingspring.entity.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightDto toDto(Flight flight) {
        return new FlightDto(
                flight.getId(),
                flight.getFromCity(),
                flight.getToCity(),
                flight.getDepartureTime(),
                flight.getAvailableSeats()
        );
    }

    public Flight toEntity(FlightDto dto) {
        Flight flight = new Flight();
        flight.setId(dto.getId());
        flight.setFromCity(dto.getFromCity());
        flight.setToCity(dto.getToCity());
        flight.setDepartureTime(dto.getDepartureTime());
        flight.setAvailableSeats(dto.getAvailableSeats());
        return flight;
    }
}
