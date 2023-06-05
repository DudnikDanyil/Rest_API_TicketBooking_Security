package rest.TicketBooking.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "airlines")
@Data
public class Airline extends BaseEntity{

    @Column(name = "company_name")
    private String companyName;

    @OneToMany(mappedBy = "airline")
    private List<Flight> flights;
}
