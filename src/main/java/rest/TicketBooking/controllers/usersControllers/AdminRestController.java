package rest.TicketBooking.controllers.usersControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.TicketBooking.dto.AdminUserDto;
import rest.TicketBooking.dto.UserDtoInput;
import rest.TicketBooking.model.User;
import rest.TicketBooking.services.MapperService;
import rest.TicketBooking.services.UserService;

@RestController
@RequestMapping(value = "/api/admin/")
public class AdminRestController {

    private final UserService userService;

    private final MapperService mapperService;

    @Autowired
    public AdminRestController(UserService userService, MapperService mapperService) {
        this.userService = userService;
        this.mapperService = mapperService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") int id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = mapperService.convertToAdminUserDto(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/registr")
    public ResponseEntity<AdminUserDto> registrationUserAdmin(@RequestBody UserDtoInput userDtoInput) {

        AdminUserDto adminUserDto = mapperService.convertToAdminUserDto(userService
                .register(mapperService.convertToUser(userDtoInput)));

        return ResponseEntity.ok(adminUserDto);
    }
}
