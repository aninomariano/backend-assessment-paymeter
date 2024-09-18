package io.paymeter.assessment.infrastructure.repository.parking;

import io.paymeter.assessment.application.exception.ParkingNotFoundException;
import io.paymeter.assessment.infrastructure.dto.ParkingEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ParkingRepositoryWrapperTest {

    private static final String P_01 = "P01";

    @Mock
    private ParkingRepository parkingRepository;

    @InjectMocks
    private ParkingRepositoryWrapper parkingRepositoryWrapper;

    @Test
    void givenParkingRepositoryWrapper_whenTryToSearchByKey_thenMustReturnObjectCorrectly() {
        final var parkingEntity = ParkingEntity.builder()
                .parkingId(P_01)
                .build();
        when(parkingRepository.findById(P_01)).thenReturn(Optional.of(parkingEntity));

        final var response = parkingRepositoryWrapper.findById(P_01);

        assertThat(response.getParkingId()).isEqualTo(P_01);
    }

    @Test
    void givenParkingRepositoryWrapper_whenTryToSearchByInvalidKey_thenMustThrowAnException() {
        when(parkingRepository.findById(P_01)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> parkingRepositoryWrapper.findById(P_01))
                .isInstanceOf(ParkingNotFoundException.class);
    }
}
