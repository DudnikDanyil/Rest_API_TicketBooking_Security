package rest.TicketBooking.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "flights")
@Data
public class Flight extends Transportation{

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "booking",
                joinColumns = {@JoinColumn(name = "flight_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
        private List<User> users;

        @ManyToOne
        @JoinColumn(name = "airline_id")
        private Airline airline;
}
