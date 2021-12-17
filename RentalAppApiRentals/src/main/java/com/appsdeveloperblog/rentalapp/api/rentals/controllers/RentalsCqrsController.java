package com.appsdeveloperblog.rentalapp.api.rentals.controllers;

import com.appsdeveloperblog.rentalapp.api.rentals.features.rentalTypes.restModels.CreateRentalTypeRestModel;
import com.appsdeveloperblog.rentalapp.api.rentals.features.rentalTypes.restModels.commands.create.CreateRentalTypeCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/rentalstypes")
public class RentalsCqrsController {

    private CommandGateway commandGateway;

    @Autowired
    public RentalsCqrsController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createRentalType(@RequestBody CreateRentalTypeRestModel createRentalTypeRestModel){
        CreateRentalTypeCommand command = CreateRentalTypeCommand.builder()
                .carId(createRentalTypeRestModel.getCarId())
                .rentDate(createRentalTypeRestModel.getRentDate())
                .userId(createRentalTypeRestModel.getUserId())
                .rentalId(UUID.randomUUID().toString())
                .build();
        String returnValue = this.commandGateway.sendAndWait(command);
        return returnValue;
    }
}
