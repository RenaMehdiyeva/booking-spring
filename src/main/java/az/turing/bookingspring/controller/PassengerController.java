package az.turing.bookingspring.controller;

import az.turing.bookingspring.dto.PassengerDto;
import az.turing.bookingspring.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/bookingApp/passenger")
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping("/register")
    public PassengerDto registerPassenger(@RequestBody PassengerDto passengerDto) {
        return passengerService.registerPassenger(passengerDto);
    }

    @PostMapping("/login")
    public PassengerDto loginPassenger(@RequestParam String login, @RequestParam String password) {
        return passengerService.loginPassenger(login, password);
    }

    @GetMapping("/fullname/{fullName}")
    public PassengerDto findByFullName(@PathVariable String fullName) {
        return passengerService.findByFullName(fullName);
    }
}
