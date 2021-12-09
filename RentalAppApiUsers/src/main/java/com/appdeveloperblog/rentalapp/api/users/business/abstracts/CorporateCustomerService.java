package com.appdeveloperblog.rentalapp.api.users.business.abstracts;

import com.appdeveloperblog.rentalapp.api.users.business.dtos.CorporateCustomerSearchListDto;
import com.appdeveloperblog.rentalapp.api.users.business.request.corporateCustomers.CreateCorporateCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.business.request.corporateCustomers.DeleteCorporateCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.business.request.corporateCustomers.UpdateCorporateCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.results.DataResult;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.results.Result;

import java.util.List;

public interface CorporateCustomerService {

    DataResult<List<CorporateCustomerSearchListDto>> getAll();
    Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
    Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
    Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);
}