package com.appdeveloperblog.rentalapp.api.users.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerSearchListDto {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
}
