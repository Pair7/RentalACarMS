package com.appdeveloperblog.rentalapp.api.users.business.abstracts;

import com.appdeveloperblog.rentalapp.api.users.business.dtos.IndividualCustomerSearchListDto;
import com.appdeveloperblog.rentalapp.api.users.business.request.individualCustomers.CreateIndividualCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.business.request.individualCustomers.DeleteIndividualCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.business.request.individualCustomers.UpdateIndividualCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.results.DataResult;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.results.Result;

import java.util.List;

public interface IndividualCustomerService {
    DataResult<List<IndividualCustomerSearchListDto>> getAll();
    Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
    Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
    Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);
}