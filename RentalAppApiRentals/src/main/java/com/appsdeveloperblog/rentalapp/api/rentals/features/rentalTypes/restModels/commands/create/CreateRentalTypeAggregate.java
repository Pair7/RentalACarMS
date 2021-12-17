package com.appsdeveloperblog.rentalapp.api.rentals.features.rentalTypes.restModels.commands.create;


import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Aggregate
public class CreateRentalTypeAggregate {

    @AggregateIdentifier
    private String rentalId;
    private int carId;
    private int userId;
    private LocalDate rentDate;

    public CreateRentalTypeAggregate (){

    }

    @CommandHandler
    public CreateRentalTypeAggregate (CreateRentalTypeCommand createRentalTypeCommand){

        //validation
        if (createRentalTypeCommand.getUserId() < 0  ){
             throw new IllegalArgumentException("User bulunamdi");
        }

        RentalTypeCreatedEvent rentalTypeCreatedEvent = new RentalTypeCreatedEvent();
        BeanUtils.copyProperties(createRentalTypeCommand,rentalTypeCreatedEvent);
        AggregateLifecycle.apply(rentalTypeCreatedEvent);
    }

    @EventSourcingHandler
    public void on(RentalTypeCreatedEvent rentalTypeCreatedEvent){
        this.userId = rentalTypeCreatedEvent.getUserId();
        this.rentalId = rentalTypeCreatedEvent.getRentalId();
        this.rentDate = rentalTypeCreatedEvent.getRentDate();
        this.carId = rentalTypeCreatedEvent.getCarId();

    }
}
