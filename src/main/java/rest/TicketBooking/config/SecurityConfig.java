package rest.TicketBooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import rest.TicketBooking.security.jwt.JwtConfigurer;
import rest.TicketBooking.security.jwt.JwtTokenProvider;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final SecurityConstants securityConstants;

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider, SecurityConstants securityConstants) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.securityConstants = securityConstants;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(securityConstants.LOGIN_ENDPOINT).permitAll()
                .antMatchers(securityConstants.ADMIN_ENDPOINT,
                        securityConstants.DELETE_USER_ENDPOINT).hasAnyRole("ADMIN", "SUPER_ADMIN")
                .antMatchers(securityConstants.REGISTRATION_ADMIN_ENDPOINT).hasRole("SUPER_ADMIN")
                .antMatchers(securityConstants.REGISTRATION_ENDPOINT).permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));

    }
}

