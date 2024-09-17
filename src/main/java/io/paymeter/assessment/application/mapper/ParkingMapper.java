package io.paymeter.assessment.application.mapper;

import io.paymeter.assessment.application.exception.InvalidDateException;
import io.paymeter.assessment.domain.dto.ParkingCalculation;
import io.paymeter.assessment.application.dto.request.ParkingRequest;
import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface ParkingMapper {

    @Mapping(source = "parkingRequest.from", target = "from", qualifiedByName = "date")
    @Mapping(source = "parkingRequest.to", target = "to", qualifiedByName = "date")
    ParkingCalculation map(final ParkingRequest parkingRequest);

    @Named("date")
    default LocalDateTime parseDate(final String date) {
        try {
            return LocalDateTime.parse(date);
        } catch (final IllegalArgumentException | UnsupportedOperationException e) {
            throw new InvalidDateException(e);
        }
    }

}
