package io.paymeter.assessment.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DateServiceTest {

    @Mock
    private Clock clock;

    @InjectMocks
    private DateServiceImpl dateService;

    @Test
    void givenDateService_whenTryToGetInstant_thenMustReturnOk() {
        when(clock.getZone()).thenReturn(ZoneId.of("Z"));
        when(clock.instant()).thenReturn(Instant.parse("2024-09-04T08:07:00Z"));

        assertThat(dateService.getInstant()).isInstanceOf(LocalDateTime.class);
        assertThat(dateService.getInstant()).isNotNull();
    }
}
