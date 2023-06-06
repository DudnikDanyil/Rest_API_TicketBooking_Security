package rest.TicketBooking.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public class Transportation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "departure_city")
    private String departure_city;

    @Column(name = "arrival_city")
    private String arrival_city;

    @Column(name = "date_and_time_of_departure")
    private LocalDateTime date_and_time_of_departure;

    @Column(name = "date_and_time_of_arrival")
    private LocalDateTime date_and_time_of_arrival;

    @Column(name = "price")
    private BigDecimal price;
}
