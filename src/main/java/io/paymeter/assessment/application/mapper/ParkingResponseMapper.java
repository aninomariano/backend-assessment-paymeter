package io.paymeter.assessment.application.mapper;

import io.paymeter.assessment.application.dto.response.ParkingResponse;
import io.paymeter.assessment.application.exception.InvalidDateFormatException;
import io.paymeter.assessment.domain.dto.ParkingCalculation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;


@Mapper(componentModel = "spring")
public interface ParkingResponseMapper {

    @Mapping(source = "parkingCalculation.from", target = "from", qualifiedByName = "date")
    @Mapping(source = "parkingCalculation.to", target = "to", qualifiedByName = "date")
    ParkingResponse map(ParkingCalculation parkingCalculation);

    @Named("date")
    default String parseDate(final LocalDateTime date) {
        try {
            return date.toString();
        } catch (final NullPointerException e) {
            throw new InvalidDateFormatException(e);
        }
    }

}
