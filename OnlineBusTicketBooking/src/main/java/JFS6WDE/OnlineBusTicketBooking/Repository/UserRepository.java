package JFS6WDE.OnlineBusTicketBooking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import JFS6WDE.OnlineBusTicketBooking.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}