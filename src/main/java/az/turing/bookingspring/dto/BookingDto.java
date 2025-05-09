package az.turing.bookingspring.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BookingDto {
    private Long id;
    private FlightDto flight;
    private LocalDateTime createdAt;
    private List<PassengerDto> passengers;
    private int numberOfTickets;
}
