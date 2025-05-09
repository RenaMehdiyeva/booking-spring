package az.turing.bookingspring.repository;

import az.turing.bookingspring.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByFromCityAndDepartureTimeBetween(String fromCity, LocalDateTime start, LocalDateTime end);

    List<Flight> findByToCityAndDepartureTimeBetween(String toCity, LocalDateTime start, LocalDateTime end);
}
