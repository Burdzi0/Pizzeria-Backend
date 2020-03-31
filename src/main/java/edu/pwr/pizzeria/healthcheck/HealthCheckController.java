package edu.pwr.pizzeria.healthcheck;

import edu.pwr.pizzeria.common.TimeProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class HealthCheckController {

    private TimeProvider timeProvider;

    public HealthCheckController(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    @GetMapping(value = "/v1/health-check", produces = APPLICATION_JSON_VALUE)
    public HealthCheck getStatus() {
        // TODO add database connection to check
        return new HealthCheck(HealthStatus.HEALTHY, timeProvider.getTime());
    }
}
