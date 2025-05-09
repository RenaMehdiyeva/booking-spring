package az.turing.bookingspring.controller;

import az.turing.bookingspring.dto.FlightDto;
import az.turing.bookingspring.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public void saveFlight(@RequestBody FlightDto flightDto) {
        flightService.saveFlight(flightDto);
    }

    @GetMapping
    public List<FlightDto> getAllFlights() {
        return flightService.findAll();
    }

    @PutMapping("/{id}")
    public void updateFlight(@PathVariable int id,
                             @RequestBody FlightDto flightDto) {
        flightService.updateFlightInfo(id, flightDto);
    }

    @GetMapping("/next24")
    public List<FlightDto> getNext24Flights() {
        return flightService.getFlightsInNext24Hours();
    }

    @GetMapping("/search")
    public List<FlightDto> searchFlights(@RequestParam String toCity,
                                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                         @RequestParam int passengers) {
        return flightService.searchFlights(toCity, date, passengers);
    }
}
