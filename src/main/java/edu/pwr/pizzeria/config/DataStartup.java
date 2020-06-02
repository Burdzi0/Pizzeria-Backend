package edu.pwr.pizzeria.config;

import edu.pwr.pizzeria.model.authentication.CredentialsDto;
import edu.pwr.pizzeria.model.user.Role;
import edu.pwr.pizzeria.repository.CustomerUserRepository;
import edu.pwr.pizzeria.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


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
        repository.getCustomerUserByMail(email)
                .ifPresentOrElse(
                        user -> {
                        },
                        () -> {
                            authenticationService.registerAdmin(new CredentialsDto(email, password));
                            repository.getCustomerUserByMail(email)
                                    .ifPresent(user -> {
                                        user.setRoles(Role.ROLE_ADMIN);
                                        repository.save(user);
                                    });
                        }
                );
    }
}
