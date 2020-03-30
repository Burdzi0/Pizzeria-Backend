package edu.pwr.pizzeria.healthcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class HealthCheckController {

    @GetMapping(value = "/v1/health-check", produces = APPLICATION_JSON_VALUE)
    public HealthCheck getStatus() {
        // TODO add database connection to check
        return new HealthCheck(HealthStatus.HEALTHY, ZonedDateTime.now(ZoneId.of("UTC")));
    }
}
