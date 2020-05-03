package edu.pwr.pizzeria.security;

import edu.pwr.pizzeria.model.user.CustomerUser;
import edu.pwr.pizzeria.repository.CustomerUserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Primary
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private CustomerUserRepository customerUserRepository;

    public CustomerUserDetailsService(CustomerUserRepository customerUserRepository) {
        this.customerUserRepository = customerUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        final CustomerUser user = customerUserRepository.getCustomerUserByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));

        return User.withUsername(username)
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}