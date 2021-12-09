package com.appdeveloperblog.rentalapp.api.users.business.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserRequest {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 6,max = 20)
    private String password;
}

