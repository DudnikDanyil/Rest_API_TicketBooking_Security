package rest.TicketBooking.dto;

import lombok.Data;

@Data
public class UserDtoOutput {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

}
