package edu.pwr.pizzeria.service.authentication;

import edu.pwr.pizzeria.exception.UserNotFoundException;
import edu.pwr.pizzeria.exception.VerificationTokenNotFound;
import edu.pwr.pizzeria.model.authentication.VerificationEntity;
import edu.pwr.pizzeria.repository.CustomerUserRepository;
import edu.pwr.pizzeria.repository.VerificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class VerificationService {

    private VerificationRepository verificationRepository;
    private CustomerUserRepository customerUserRepository;

    public VerificationService(VerificationRepository verificationRepository, CustomerUserRepository customerUserRepository) {
        this.verificationRepository = verificationRepository;
        this.customerUserRepository = customerUserRepository;
    }

    @Transactional
    public void confirmRegistration(UUID uuid) {
        final var verificationEntity = verificationRepository.findById(uuid)
                .orElseThrow(() -> new VerificationTokenNotFound("Invalid link"));

        final var customerUser = verificationEntity.getUser();

        customerUser.setActive(true);
        customerUserRepository.save(customerUser);

        verificationRepository.delete(verificationEntity);
    }

    @Transactional
    public UUID createNewVerification(String mail) {
        final var verificationId = UUID.randomUUID();
        customerUserRepository.getCustomerUserByMail(mail)
                .map(user -> new VerificationEntity(verificationId, user))
                .map(verificationRepository::save)
                .orElseThrow(() -> new UserNotFoundException("User not registered"));

        return verificationId;
    }
}
