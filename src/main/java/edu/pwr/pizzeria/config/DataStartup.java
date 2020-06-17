package edu.pwr.pizzeria.config;

import edu.pwr.pizzeria.model.authentication.CredentialsDto;
import edu.pwr.pizzeria.model.user.Role;
import edu.pwr.pizzeria.repository.CustomerUserRepository;
import edu.pwr.pizzeria.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class DataStartup implements CommandLineRunner {

    @Value("${app.admin.email}")
    private String email;

    @Value("${app.admin.password}")
    private String password;

    private AuthenticationService authenticationService;
    private CustomerUserRepository repository;

    public DataStartup(AuthenticationService authenticationService, CustomerUserRepository repository) {
        this.authenticationService = authenticationService;
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        ensureUserExists(email, password, Role.ROLE_ADMIN);
        ensureUserExists("delivery@delivery.com", "password", Role.ROLE_DELIVERY);
        ensureUserExists("cook@cook.com", "password", Role.ROLE_COOK);
    }

    @Transactional
    public void ensureUserExists(String email, String password, Role role) {
        repository.getCustomerUserByMail(email)
                .ifPresentOrElse(
                        user -> {
                            user.setRoles(role);
                            repository.save(user);
                        },
                        () -> {
                            authenticationService.registerAdmin(new CredentialsDto(email, password));
                            repository.getCustomerUserByMail(email)
                                    .ifPresent(user -> {
                                        user.setRoles(role);
                                        repository.save(user);
                                    });
                        }
                );
    }
}
