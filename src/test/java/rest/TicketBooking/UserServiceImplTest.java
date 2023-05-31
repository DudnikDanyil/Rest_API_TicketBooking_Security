package rest.TicketBooking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rest.TicketBooking.model.Role;
import rest.TicketBooking.model.User;
import rest.TicketBooking.repositories.RoleRepository;
import rest.TicketBooking.repositories.UserRepository;
import rest.TicketBooking.services.impl.UserServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Random;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    private UserServiceImpl userService;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, roleRepository, passwordEncoder);


        // Создание пользователя перед каждым тестом
        user = new User();

        Random random = new Random();

        String username = "test" + random.nextInt(1000);
        String firstName = "test" + random.nextInt(1000);
        String lastName = "test" + random.nextInt(1000);
        String email = "test" + random.nextInt(1000) + "@gmail.com";
        String password = "test" + random.nextInt(1000);

        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
    }

    @Test
    void testRegister_SuccessfulRegistration() {

        // Arrange
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");

        when(roleRepository.findByName("ROLE_USER")).thenReturn(roleUser);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User registeredUser = userService.register(user);

        // Assert
        verify(userRepository).save(user);
        assertEquals(user.getUsername(), registeredUser.getUsername());
        assertEquals(user.getFirstName(), registeredUser.getFirstName());
        assertEquals(user.getLastName(), registeredUser.getLastName());
        assertEquals(user.getEmail(), registeredUser.getEmail());
        assertEquals(user.getPassword(), registeredUser.getPassword());
    }
}