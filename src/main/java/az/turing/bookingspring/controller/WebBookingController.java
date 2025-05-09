package az.turing.bookingspring.controller;

import az.turing.bookingspring.dto.BookingDto;
import az.turing.bookingspring.dto.FlightDto;
import az.turing.bookingspring.dto.PassengerDto;
import az.turing.bookingspring.service.BookingService;
import az.turing.bookingspring.service.FlightService;
import az.turing.bookingspring.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class WebBookingController {
    private final BookingService bookingService;
    private final FlightService flightService;
    private final PassengerService passengerService;

    @Autowired
    public WebBookingController(BookingService bookingService, FlightService flightService, PassengerService passengerService) {
        this.bookingService = bookingService;
        this.flightService = flightService;
        this.passengerService = passengerService;
    }

    @GetMapping("/console")
    public String showConsole(Model model) {
        model.addAttribute("flights", flightService.getFlightsFromKievNext24Hours());
        return "console";
    }

    @GetMapping("/flight-info")
    public String showFlightInfo(@RequestParam Long id, Model model) {
        try {
            model.addAttribute("flight", flightService.getFlightById(id));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "console";
    }

    @GetMapping("/search-book")
    public String showSearchForm() {
        return "search-book";
    }

    @PostMapping("/search-book")
    public String searchAndBook(@RequestParam String destination, @RequestParam String date,
                                @RequestParam int numberOfTickets, Model model) {
        LocalDateTime dateTime = LocalDateTime.parse(date);
        List<FlightDto> flights = flightService.searchFlights(destination, dateTime, numberOfTickets);
        model.addAttribute("flights", flights);
        model.addAttribute("numberOfTickets", numberOfTickets);
        return "search-book";
    }

    @PostMapping("/confirm-book")
    public String confirmBooking(@RequestParam Long flightId, @RequestParam int numberOfTickets,
                                 @RequestParam Long passengerId, Model model) {
        BookingDto bookingDto = BookingDto.builder()
                .flight(FlightDto.builder().id(flightId).build())
                .passengers(List.of(PassengerDto.builder().id(passengerId).build()))
                .numberOfTickets(numberOfTickets)
                .build();
        try {
            BookingDto booked = bookingService.createBooking(bookingDto);
            model.addAttribute("message", "Booking successful! ID: " + booked.getId());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "console";
    }

    @GetMapping("/cancel")
    public String showCancelForm() {
        return "cancel";
    }

    @PostMapping("/cancel")
    public String cancelBooking(@RequestParam Long bookingId, Model model) {
        try {
            bookingService.cancelBooking(bookingId);
            model.addAttribute("message", "Booking cancelled successfully!");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "console";
    }

    @GetMapping("/my-bookings")
    public String showMyBookings(@RequestParam String fullName, Model model) {
        try {
            PassengerDto passenger = passengerService.findByFullName(fullName);
            List<BookingDto> bookings = bookingService.getBookingsByPassengerId(passenger.getId());
            model.addAttribute("bookings", bookings);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "console";
    }
}
