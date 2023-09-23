package rest.TicketBooking.controllers.usersControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.TicketBooking.dto.UserDtoInput;
import rest.TicketBooking.dto.UserDtoOutput;
import rest.TicketBooking.model.User;
import rest.TicketBooking.services.MapperService;
import rest.TicketBooking.services.UserService;


@RestController
@RequestMapping(value = "/api/users/")
public class UserRestController {
    private final UserService userService;
    private final MapperService mapperService;

    @Autowired
    public UserRestController(UserService userService,
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

    @PostMapping(value = "/registr")
    public ResponseEntity<UserDtoOutput> registrationUser(@RequestBody UserDtoInput userDtoInput) {

        UserDtoOutput userDtoOutput = mapperService.convertToUserDto(userService
                .register(mapperService.convertToUser(userDtoInput)));

        return ResponseEntity.ok(userDtoOutput);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deletingUser(@RequestParam("id") Integer id) {

        userService.delete(id);

        return ResponseEntity.ok().build();
    }

}
