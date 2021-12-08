package com.appsdeveloperblog.rentalapp.api.rentals.business.requests.color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {
    private int colorId;
    private String colorName;
}