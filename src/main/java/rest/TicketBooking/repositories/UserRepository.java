package rest.TicketBooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.TicketBooking.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String username);
}
