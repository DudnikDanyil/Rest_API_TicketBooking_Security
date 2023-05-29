package rest.TicketBooking.controllers.usersControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.TicketBooking.services.UserService;


@RequestMapping(value = "/api")
@RestController
public class DeleteRestController {

    private final UserService userService;

    @Autowired
    public DeleteRestController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deletingUser(@RequestParam("id") Integer id) {

        userService.delete(id);

        return ResponseEntity.ok().build();
    }
}
