package rest.TicketBooking.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rest.TicketBooking.exceptions.SuperAdminDeletionException;
import rest.TicketBooking.exceptions.UserAlreadyExistsException;
import rest.TicketBooking.model.Role;
import rest.TicketBooking.model.emuns.Status;
import rest.TicketBooking.model.User;
import rest.TicketBooking.repositories.RoleRepository;
import rest.TicketBooking.repositories.UserRepository;
import rest.TicketBooking.services.AdminUserService;
import rest.TicketBooking.services.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService, AdminUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {

        checkIfUserExists(user);

        Role roleUser = roleRepository.findByName("ROLE_USER");
        return registerUserWithRole(user, roleUser);
    }

    @Override
    public User registerAdmin(User user) {

        checkIfUserExists(user);

        Role roleUser = roleRepository.findByName("ROLE_ADMIN");
        return registerUserWithRole(user, roleUser);
    }

    @Override
    public List<User> getAll() {

        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {

        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Integer id) {

        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
            log.info("IN findById - user: {} found by id: {}", result);
            return result;
    }

    @Override
    public void delete(Integer id) {

        User user = userRepository.findById(id).get();
        for (Role role : user.getRoles()) {
            System.out.println(role.getName());
            if (role.getName().equals("ROLE_SUPER_ADMIN")) {
                throw new SuperAdminDeletionException("It is forbidden to delete a user with a role SUPER_ADMIN!");
            }
        }

        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }


    private User registerUserWithRole(User user, Role roleUser) {

        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    private void checkIfUserExists(User user) throws UserAlreadyExistsException {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists");
        }
    }
}
