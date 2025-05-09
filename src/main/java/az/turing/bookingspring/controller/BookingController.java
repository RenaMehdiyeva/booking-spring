package az.turing.bookingspring.controller;

import az.turing.bookingspring.dto.BookingDto;
import az.turing.bookingspring.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/bookingApp/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @GetMapping
    public BookingDto getBookingById(@PathVariable Long id) {
        return service.getBookingById(id);
    }
}
