package rest.TicketBooking.model;

import lombok.Data;

import javax.persistence.*;


@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
