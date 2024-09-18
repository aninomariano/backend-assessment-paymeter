package io.paymeter.assessment.application.controller.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HealthController {

	@GetMapping("/")
	public ResponseEntity<String> index() {
		return ResponseEntity.of(Optional.of("ok"));
	}

}
