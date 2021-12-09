package com.appdeveloperblog.rentalapp.api.users.business.request.individualCustomers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {
    private int userId;
    private String firstName;
    private String lastName;

    @Email //(message = Messages.EMAILFORMATERROR)
    private String email;
    private String password;
    @JsonIgnore
    private String encryptedPassword;
    private LocalDate birthday;
}
