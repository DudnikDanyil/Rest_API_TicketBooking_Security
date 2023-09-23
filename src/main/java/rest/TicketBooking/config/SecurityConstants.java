package rest.TicketBooking.config;

import org.springframework.stereotype.Component;

@Component
public final class SecurityConstants {

    public static final String ADMIN_ENDPOINT = "/api/admin/**";
    public static final String LOGIN_ENDPOINT = "/api/auth/login";
    public static final String REGISTRATION_ENDPOINT = "/api/users/registr";
    public static final String REGISTRATION_ADMIN_ENDPOINT = "/api/admin/registr";
    public static final String DELETE_USER_ENDPOINT = "/api/delete";
}
