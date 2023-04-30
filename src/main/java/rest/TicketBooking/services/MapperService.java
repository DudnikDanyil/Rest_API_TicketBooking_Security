package rest.TicketBooking.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.TicketBooking.dto.UserDtoInput;
import rest.TicketBooking.dto.UserDtoOutput;
import rest.TicketBooking.model.User;

@Service
public class MapperService {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User convertToUser(UserDtoInput userDtoInput) {
        return modelMapper.map(userDtoInput, User.class);
    }

    public UserDtoOutput convertToUserDto(User user) {
        return modelMapper.map(user, UserDtoOutput.class);
    }
}
