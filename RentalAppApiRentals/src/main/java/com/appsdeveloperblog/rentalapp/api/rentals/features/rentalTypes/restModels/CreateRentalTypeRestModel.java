package com.appsdeveloperblog.rentalapp.api.rentals.features.rentalTypes.restModels;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateRentalTypeRestModel {

    private int carId;
    private int userId;
    private LocalDate rentDate;

}
