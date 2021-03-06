package com.appdeveloperblog.rentalapp.api.users.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerSearchListDto {

    private String companyName;
    private String taxNumber;
    private String email;

}
