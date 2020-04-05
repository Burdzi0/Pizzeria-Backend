package edu.pwr.pizzeria.healthcheck;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableFeignClients
class HealthCheckControllerTest {

    @Autowired
    private HealthCheckClient healthCheckClient;

    @Test
    void getStatus() {
        final var healthCheck = healthCheckClient.healthCheck();
        assertThat(healthCheck).isNotNull();
        assertThat(healthCheck.getStatus()).isEqualTo(HealthStatus.HEALTHY);
        assertThat(healthCheck.getTime().getZone()).isEqualTo(ZoneId.of("Europe/Warsaw"));
    }
}