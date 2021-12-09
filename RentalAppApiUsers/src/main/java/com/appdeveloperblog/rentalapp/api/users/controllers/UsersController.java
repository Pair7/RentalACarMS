package com.appdeveloperblog.rentalapp.api.users.controllers;

import com.appdeveloperblog.rentalapp.api.users.business.abstracts.UserService;
import com.appdeveloperblog.rentalapp.api.users.business.request.user.LoginUserRequest;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working on";
    }


    @PostMapping("login")
    public Result login(@RequestBody LoginUserRequest loginUserRequest) {
        return this.userService.login(loginUserRequest);
    }

}