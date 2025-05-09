package az.turing.bookingspring.service.impl;

import az.turing.bookingspring.dto.PassengerDto;
import az.turing.bookingspring.entity.Passenger;
import az.turing.bookingspring.exceptions.PassengerException;
import az.turing.bookingspring.mapper.PassengerMapper;
import az.turing.bookingspring.repository.PassengerRepository;
import az.turing.bookingspring.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository, PassengerMapper passengerMapper) {
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
    }

    @Override
    public PassengerDto registerPassenger(PassengerDto passengerDto) {
        if (passengerRepository.findByLogin(passengerDto.getLogin()) != null) {
            throw new PassengerException("Login already exists");
        }
        Passenger passenger = passengerMapper.toEntity(passengerDto);
        passenger = passengerRepository.save(passenger);
        return passengerMapper.toDto(passenger);
    }

    @Override
    public PassengerDto loginPassenger(String login, String password) {
        Passenger passenger = passengerRepository.findByLoginAndPassword(login, password);
        if (passenger == null) {
            throw new PassengerException("Invalid login or password");
        }
        return passengerMapper.toDto(passenger);
    }

    @Override
    public PassengerDto findByFullName(String fullName) {
        Passenger passenger = passengerRepository.findByName(fullName);
        if (passenger == null) {
            throw new PassengerException("Passenger not found");
        }
        return passengerMapper.toDto(passenger);
    }
}
