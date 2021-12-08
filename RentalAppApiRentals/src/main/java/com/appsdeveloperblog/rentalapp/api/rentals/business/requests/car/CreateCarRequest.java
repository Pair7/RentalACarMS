package com.appsdeveloperblog.rentalapp.api.rentals.business.requests.car;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateCarRequest {

    @NotNull
    private int modelYear;

    @NotNull
    private int dailyPrice;

    @NotNull
    @Size(min = 2,max = 100)
    private String description;

    @NotNull
    private int colorId;

    @NotNull
    private int brandId;
}
