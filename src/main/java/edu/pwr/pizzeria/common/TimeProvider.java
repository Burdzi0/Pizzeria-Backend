package edu.pwr.pizzeria.common;

import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class TimeProvider {

    private final ZoneId zoneId = ZoneId.of("Europe/Warsaw");

    public ZonedDateTime getTime() {
        return ZonedDateTime.now(zoneId);
    }
}
