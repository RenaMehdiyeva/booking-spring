package az.turing.bookingspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "passengers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToMany(mappedBy = "passengers")
    private List<Booking> bookings;
}
