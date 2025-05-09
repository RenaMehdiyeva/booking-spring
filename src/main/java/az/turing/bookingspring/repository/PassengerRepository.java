package az.turing.bookingspring.repository;

import az.turing.bookingspring.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Passenger findByLoginAndPassword(String login, String password);
    Passenger findByName(String name);
}
