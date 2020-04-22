package edu.pwr.pizzeria.healthcheck;

import edu.pwr.pizzeria.ClientBasedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class HealthCheckControllerTest extends ClientBasedTest {

    @Autowired
    private HealthCheckClient healthCheckClient;

    @Test
    void getStatus() {
        final var healthCheck = healthCheckClient.healthCheck();
        assertThat(healthCheck).isNotNull();
        assertThat(healthCheck.getStatus()).isEqualTo(HealthStatus.HEALTHY);
    }
}