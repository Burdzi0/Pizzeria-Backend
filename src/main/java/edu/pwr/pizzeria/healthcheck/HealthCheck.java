package edu.pwr.pizzeria.healthcheck;

import java.time.ZonedDateTime;

public class HealthCheck {

    private HealthStatus status;
    private ZonedDateTime time;

    public HealthCheck(HealthStatus status, ZonedDateTime time) {
        this.status = status;
        this.time = time;
    }

    public HealthStatus getStatus() {
        return status;
    }

    public ZonedDateTime getTime() {
        return time;
    }
}
