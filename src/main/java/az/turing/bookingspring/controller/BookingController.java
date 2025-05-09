package az.turing.bookingspring.controller;

import az.turing.bookingspring.dto.BookingDto;
import az.turing.bookingspring.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bookingApp/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @GetMapping("/{id}")
    public BookingDto getBookingById(@PathVariable Long id) {
        return service.getBookingById(id);
    }

    @PostMapping
    public BookingDto createBooking(@RequestBody BookingDto bookingDto) {
        return service.createBooking(bookingDto);
    }

    @DeleteMapping("/{id}")
    public void cancelBooking(@PathVariable Long id) {
        service.cancelBooking(id);
    }

    @GetMapping("/passenger/{passengerId}")
    public List<BookingDto> getBookingsByPassengerId(@PathVariable Long passengerId) {
        return service.getBookingsByPassengerId(passengerId);
    }
}