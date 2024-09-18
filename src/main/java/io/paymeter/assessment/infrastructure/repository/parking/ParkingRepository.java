package io.paymeter.assessment.infrastructure.repository.parking;

import io.paymeter.assessment.infrastructure.dto.ParkingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends CrudRepository<ParkingEntity, String> {}
