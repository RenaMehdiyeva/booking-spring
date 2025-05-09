package az.turing.bookingspring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassengerDto {
    private Long id;
    private String name;
    private String login;
    private String password;
}
