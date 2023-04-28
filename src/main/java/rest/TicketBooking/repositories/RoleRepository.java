package rest.TicketBooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.TicketBooking.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
