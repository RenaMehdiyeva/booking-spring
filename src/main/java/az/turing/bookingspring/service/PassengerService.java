package az.turing.bookingspring.service;

import az.turing.bookingspring.dto.PassengerDto;

public interface PassengerService {
    PassengerDto registerPassenger(PassengerDto passengerDto);
    PassengerDto loginPassenger(String login, String password);
    PassengerDto findByFullName(String fullName);
}
