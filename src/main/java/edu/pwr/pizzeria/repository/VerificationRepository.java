package edu.pwr.pizzeria.repository;

import edu.pwr.pizzeria.model.authentication.VerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VerificationRepository extends JpaRepository<VerificationEntity, UUID> {
}
