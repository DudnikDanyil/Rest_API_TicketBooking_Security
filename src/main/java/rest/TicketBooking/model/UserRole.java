package rest.TicketBooking.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
@Data
public class UserRole{

    @EmbeddedId
    private UserRoleKey id;

    @ManyToOne
    @MapsId("roleId")
    private Role role;

    @ManyToOne
    @MapsId("userId")
    private User user;

}
