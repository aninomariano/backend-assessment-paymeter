package io.paymeter.assessment.application.controller.health;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;

import static org.assertj.core.api.Assertions.assertThat;

public class HealthControllerTest {

    private HealthController healthController;

    @BeforeEach
    void setUp() {
        healthController = new HealthController();
    }

    @Test
    void givenHealthController_whenTryToCheckIndex_thenMustRespondResponseEntityWithStringOk() {
        final var indexResponse = healthController.index();

        assertThat(indexResponse.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        assertThat(indexResponse.getBody()).isEqualTo("ok");
    }
}
