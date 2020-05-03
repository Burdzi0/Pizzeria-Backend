package edu.pwr.pizzeria.repository;

import edu.pwr.pizzeria.model.user.CustomerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerUserRepository extends JpaRepository<CustomerUser, Long> {

    Optional<CustomerUser> getCustomerUserByMail(String mail);
}
