package io.paymeter.assessment.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DateServiceImpl implements DateService {

    private final Clock clock;

    @Override
    public LocalDateTime getInstant() {
        return LocalDateTime.now(clock);
    }
}
