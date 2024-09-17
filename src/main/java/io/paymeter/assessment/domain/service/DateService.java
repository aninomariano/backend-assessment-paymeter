package io.paymeter.assessment.domain.service;

import java.time.LocalDateTime;

public interface DateService {

    /**
     * Get local date time instant.
     *
     * @return the local date time
     */
    LocalDateTime getInstant();
}
