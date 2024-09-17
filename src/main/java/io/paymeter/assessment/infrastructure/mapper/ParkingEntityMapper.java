package io.paymeter.assessment.infrastructure.mapper;

import io.paymeter.assessment.application.dto.request.ParkingRequest;
import io.paymeter.assessment.application.exception.InvalidDateException;
import io.paymeter.assessment.domain.dto.ParkingCalculation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Mapper(componentModel = "spring")
public interface ParkingEntityMapper {

    @Mapping(source = "parkingRequest.from", target = "from", qualifiedByName = "date")
    @Mapping(source = "parkingRequest.to", target = "to", qualifiedByName = "date")
    @Mapping(target = "money", ignore = true)
    @Mapping(target = "duration", ignore = true)
    @Mapping(target = "price", ignore = true)
    ParkingCalculation map(final ParkingRequest parkingRequest);

    @Named("date")
    default LocalDateTime parseDate(final String date) {
        try {
            return LocalDateTime.parse(date);
        } catch (final NullPointerException e) {
            return null;
        } catch (final DateTimeParseException | IllegalArgumentException | UnsupportedOperationException e) {
            throw new InvalidDateException(e);
        }
    }

}
