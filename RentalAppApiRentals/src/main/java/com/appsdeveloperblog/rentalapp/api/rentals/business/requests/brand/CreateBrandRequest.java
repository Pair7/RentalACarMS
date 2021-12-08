package com.appsdeveloperblog.rentalapp.api.rentals.business.requests.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {
    private String brandName;
}