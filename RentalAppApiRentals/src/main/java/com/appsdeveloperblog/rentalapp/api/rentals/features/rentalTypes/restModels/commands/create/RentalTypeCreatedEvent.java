package com.appsdeveloperblog.rentalapp.api.rentals.features.rentalTypes.restModels.commands.create;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RentalTypeCreatedEvent {

    private String rentalId;
    private int carId;
    private int userId;
    private LocalDate rentDate;
}
