package rest.TicketBooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.TicketBooking.dto.UserDtoOutput;
import rest.TicketBooking.model.User;
import rest.TicketBooking.services.MapperService;
import rest.TicketBooking.services.UserService;


@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {
    private final UserService userService;
    private final MapperService mapperService;

    @Autowired
    public UserRestControllerV1(UserService userService,
                                MapperService mapperService) {
        this.userService = userService;
        this.mapperService = mapperService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDtoOutput> getUserById(@PathVariable(name = "id") int id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDtoOutput result = mapperService.convertToUserDto(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
