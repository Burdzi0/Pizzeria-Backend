package edu.pwr.pizzeria.common;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class TimeProvider {

    public static final String EUROPE_WARSAW = "Europe/Warsaw";

    public ZonedDateTime getTime() {
        final ZoneId zone = ZoneId.of(EUROPE_WARSAW);
        return ZonedDateTime.of(LocalDateTime.now(), zone);
    }
}
