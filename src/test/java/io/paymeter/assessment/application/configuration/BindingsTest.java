package io.paymeter.assessment.application.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;

import static org.assertj.core.api.Assertions.assertThat;

public class BindingsTest {

    private Bindings bindings;

    @BeforeEach
    void setUp() {
        bindings = new Bindings();
    }

    @Test
    void givenClockWithUtcTime_whenTryToGetClockAndCheckSystem_thenMustGoAllOk() {
        assertThat(bindings.getClock()).isEqualTo(Clock.systemUTC());
    }
}
