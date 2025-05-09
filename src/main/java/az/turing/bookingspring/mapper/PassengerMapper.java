package az.turing.bookingspring.mapper;

import az.turing.bookingspring.dto.PassengerDto;
import az.turing.bookingspring.entity.Passenger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PassengerMapper {
    public PassengerDto toDto(Passenger passenger) {
        return PassengerDto.builder()
                .id(passenger.getId())
                .name(passenger.getName())
                .login(passenger.getLogin())
                .password(passenger.getPassword())
                .build();
    }

    public Passenger toEntity(PassengerDto dto) {
        return Passenger.builder()
                .id(dto.getId())
                .name(dto.getName())
                .login(dto.getLogin())
                .password(dto.getPassword())
                .build();
    }
}
