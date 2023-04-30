package rest.TicketBooking.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import rest.TicketBooking.model.Status;
import rest.TicketBooking.model.User;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String status;

}
