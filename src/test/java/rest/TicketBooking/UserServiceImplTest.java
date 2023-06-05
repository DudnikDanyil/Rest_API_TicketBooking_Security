package rest.TicketBooking;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rest.TicketBooking.exceptions.UserAlreadyExistsException;
import rest.TicketBooking.model.Role;
import rest.TicketBooking.model.User;
import rest.TicketBooking.repositories.RoleRepository;
import rest.TicketBooking.repositories.UserRepository;
import rest.TicketBooking.services.impl.UserServiceImpl;

import javax.persistence.EntityNotFoundException;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
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

        user = new User();

        Random random = new Random();

        Integer userId = 1;
        String username = "test" + random.nextInt(1000);
        String firstName = "test" + random.nextInt(1000);
        String lastName = "test" + random.nextInt(1000);
        String email = "test" + random.nextInt(1000) + "@gmail.com";
        String password = "test" + random.nextInt(1000);

        user.setId(userId);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
    }

    @Test
    void testRegister_SuccessfulRegistration() {

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");

        when(roleRepository.findByName("ROLE_USER")).thenReturn(roleUser);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User registeredUser = userService.register(user);

        verify(userRepository).save(user);
        assertEquals(user.getUsername(), registeredUser.getUsername());
        assertEquals(user.getFirstName(), registeredUser.getFirstName());
        assertEquals(user.getLastName(), registeredUser.getLastName());
        assertEquals(user.getEmail(), registeredUser.getEmail());
        assertEquals(user.getPassword(), registeredUser.getPassword());

        verify(roleRepository, Mockito.times(1)).findByName("ROLE_USER");
        verify(userRepository, Mockito.times(1)).save(any(User.class));
    }

    @Test
    void testRegisterAdmin_SuccessfulRegistration() {

        Role roleUser = new Role();
        roleUser.setName("ROLE_ADMIN");

        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(roleUser);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User registeredUser = userService.registerAdmin(user);

        verify(userRepository).save(user);
        assertEquals(user.getUsername(), registeredUser.getUsername());
        assertEquals(user.getFirstName(), registeredUser.getFirstName());
        assertEquals(user.getLastName(), registeredUser.getLastName());
        assertEquals(user.getEmail(), registeredUser.getEmail());
        assertEquals(user.getPassword(), registeredUser.getPassword());

        verify(roleRepository, Mockito.times(1)).findByName("ROLE_ADMIN");
        verify(userRepository, Mockito.times(1)).save(any(User.class));
    }

    @Test
    public void testFindById_ExistingId_ReturnsUser() {

        Integer userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.findById(userId);

        assertEquals(user, result);
        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
    }

    @Test
    public void testFindById_InvalidId_ThrowsException() {

        Integer userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            userService.findById(userId);
        });

        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
    }

    @Test
    public void testPrivateCheckIfUserExists_DuplicateUsernameAndEmail_ThrowsException() {

        when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);


        Method checkIfUserExistsMethod;
        try {
            checkIfUserExistsMethod = UserServiceImpl.class.getDeclaredMethod("checkIfUserExists", User.class);
            checkIfUserExistsMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Failed to access private method", e);
        }

        try {
            checkIfUserExistsMethod.invoke(userService, user);
            Assert.fail("Expected UserAlreadyExistsException, but no exception was thrown");
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            Assert.assertTrue(cause instanceof UserAlreadyExistsException);
        } catch (IllegalAccessException e) {
            throw new AssertionError("Failed to invoke private method", e);
        }
    }
}