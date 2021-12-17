package com.appsdeveloperblog.rentalapp.api.rentals.features.rentalTypes.restModels.commands.create;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;

@Data
@Builder
public class CreateRentalTypeCommand {

    @TargetAggregateIdentifier
    private final String rentalId;
    private final int carId;
    private final int userId;
    private final LocalDate rentDate;
}
