package rest.TicketBooking.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "flights")
@Data
public class Flight extends BaseEntity{

        @Column(name = "departure_city")
        private String departure_city;

        @Column(name = "arrival_city")
        private String arrival_city;

        @Column(name = "date_and_time_of_departure")
        private LocalDateTime date_and_time_of_departure;

        @Column(name = "date_and_time_of_arrival")
        private LocalDateTime date_and_time_of_arrival;

        @Column(name = "flight_price")
        private BigDecimal flight_price;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "booking",
                joinColumns = {@JoinColumn(name = "flight_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
        private List<User> users;

        @ManyToOne
        @JoinColumn(name = "airline_id")
        private Airline airline;
}
