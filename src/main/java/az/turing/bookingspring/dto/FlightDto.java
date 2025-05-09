package az.turing.bookingspring.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class FlightDto {

    private Long id;
    private String fromCity;
    private String toCity;
    private LocalDateTime departureTime;
    private int availableSeats;
}
