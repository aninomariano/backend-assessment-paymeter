package io.paymeter.assessment.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class Bindings {

    @Bean
    public Clock getClock() {
        return Clock.systemUTC();
    }
}
