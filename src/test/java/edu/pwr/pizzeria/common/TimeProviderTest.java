package edu.pwr.pizzeria.common;

import edu.pwr.pizzeria.StandardTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class TimeProviderTest extends StandardTest {

    @Autowired
    private TimeProvider timeProvider;

    @Test
    void getTime() {
        assertThat(timeProvider.getTime()).isNotNull();
        assertThat(timeProvider.getTime().getZone()).isEqualTo(ZoneId.of("Europe/Warsaw"));
    }
}