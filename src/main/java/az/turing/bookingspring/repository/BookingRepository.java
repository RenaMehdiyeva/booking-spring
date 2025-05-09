package az.turing.bookingspring.repository;

import az.turing.bookingspring.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByPassengersId(Long passengerId);
}
