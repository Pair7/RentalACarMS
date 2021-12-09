package com.appdeveloperblog.rentalapp.api.users.controllers;

import com.appdeveloperblog.rentalapp.api.users.business.abstracts.IndividualCustomerService;
import com.appdeveloperblog.rentalapp.api.users.business.dtos.IndividualCustomerSearchListDto;
import com.appdeveloperblog.rentalapp.api.users.business.request.individualCustomers.CreateIndividualCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.business.request.individualCustomers.DeleteIndividualCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.business.request.individualCustomers.UpdateIndividualCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.results.DataResult;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/individualCustomer")
public class IndividualCustomersController {

    private IndividualCustomerService individualCustomerService;

    @Autowired
    private IndividualCustomersController(IndividualCustomerService individualCustomerService) {
        super();
        this.individualCustomerService = individualCustomerService;
    }

    @GetMapping("getAll")
    public DataResult<List<IndividualCustomerSearchListDto>> getAll() {
        return this.individualCustomerService.getAll();
    }
    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        return this.individualCustomerService.add(createIndividualCustomerRequest);
    }
    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
        return this.individualCustomerService.update(updateIndividualCustomerRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
        return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
    }
}
