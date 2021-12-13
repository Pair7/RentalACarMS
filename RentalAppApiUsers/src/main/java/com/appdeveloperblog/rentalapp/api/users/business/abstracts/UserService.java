package com.appdeveloperblog.rentalapp.api.users.business.abstracts;

import com.appdeveloperblog.rentalapp.api.users.business.dtos.UserSearchListDto;
import com.appdeveloperblog.rentalapp.api.users.business.request.user.LoginUserRequest;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.results.DataResult;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.results.Result;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    DataResult<UserSearchListDto> getByEmail(String email);
    Result checkIfEmailExists(String email);
    DataResult<UserSearchListDto> getById(int id);
    Result checkIfUserExists(int userId);
    UserSearchListDto getUserDetailsByEmail(String email);
}
