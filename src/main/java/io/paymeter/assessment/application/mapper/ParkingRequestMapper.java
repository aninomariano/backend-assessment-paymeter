package io.paymeter.assessment.application.mapper;

import io.paymeter.assessment.application.exception.InvalidDateFormatException;
import io.paymeter.assessment.domain.dto.ParkingCalculation;
import io.paymeter.assessment.application.dto.request.ParkingRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ParkingRequestMapper {

    @Mapping(source = "parkingRequest.from", target = "from", qualifiedByName = "date")
    @Mapping(source = "parkingRequest.to", target = "to", qualifiedByName = "date")
    @Mapping(target = "money", ignore = true)
    @Mapping(target = "duration", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "discounts", ignore = true)
    ParkingCalculation map(final ParkingRequest parkingRequest);

    @Named("date")
    default LocalDateTime parseDate(final String date) {
        try {
            return LocalDateTime.parse(date);
        } catch (final NullPointerException e) {
            return null;
        } catch (final DateTimeParseException | IllegalArgumentException | UnsupportedOperationException e) {
            throw new InvalidDateFormatException(e);
        }
    }

}
