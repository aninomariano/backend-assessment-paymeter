package io.paymeter.assessment.application.configuration;

import org.springframework.context.annotation.Bean;

import java.time.Clock;

@org.springframework.context.annotation.Configuration
public class Bindings {

    @Bean
    public Clock getClock() {
        return Clock.systemUTC();
    }
}
