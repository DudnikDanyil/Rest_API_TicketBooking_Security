package rest.TicketBooking.dto;

import lombok.Data;

@Data
public class UserDtoInput {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
