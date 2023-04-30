package rest.TicketBooking.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.TicketBooking.dto.UserDtoInput;
import rest.TicketBooking.dto.UserDtoOutput;
import rest.TicketBooking.services.MapperService;
import rest.TicketBooking.services.UserService;

@RestController
@RequestMapping(value = "/api/")
public class RegistrationRestController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final MapperService mapperService;

    @Autowired
    public RegistrationRestController(ModelMapper modelMapper, UserService userService, MapperService mapperService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.mapperService = mapperService;
    }

    @PostMapping(value = "registr")
    public ResponseEntity<UserDtoOutput> registrationUser(@RequestBody UserDtoInput userDtoInput) {

        UserDtoOutput userDtoOutput = mapperService.convertToUserDto(userService
                .register(mapperService.convertToUser(userDtoInput)));

        return ResponseEntity.ok(userDtoOutput);
    }


}
