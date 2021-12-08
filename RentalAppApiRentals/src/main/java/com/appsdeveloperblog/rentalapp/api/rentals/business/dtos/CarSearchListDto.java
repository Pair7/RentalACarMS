package com.appsdeveloperblog.rentalapp.api.rentals.business.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarSearchListDto {

    private int carId;

    private int modelYear;

    private int dailyPrice;

    private String description;


}
