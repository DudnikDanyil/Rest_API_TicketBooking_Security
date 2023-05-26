package rest.TicketBooking.config;

import org.springframework.stereotype.Component;

@Component
public final class SecurityConstants {

    public static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    public static final String LOGIN_ENDPOINT = "/api/v1/auth/login";
    public static final String REGISTRATION_ENDPOINT = "/api/registr";
    public static final String REGISTRATION_ADMIN_ENDPOINT = "/api/registr/admin";
    public static final String DELETE_USER_ENDPOINT = "/api/delete";
}
