package JFS6WDE.OnlineBusTicketBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import JFS6WDE.OnlineBusTicketBooking.Entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
