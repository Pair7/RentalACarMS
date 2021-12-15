package com.appdeveloperblog.rentalapp.api.users.controllers;

import com.appdeveloperblog.rentalapp.api.users.business.abstracts.CorporateCustomerService;
import com.appdeveloperblog.rentalapp.api.users.business.dtos.CorporateCustomerSearchListDto;
import com.appdeveloperblog.rentalapp.api.users.business.request.corporateCustomers.CreateCorporateCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.business.request.corporateCustomers.DeleteCorporateCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.business.request.corporateCustomers.UpdateCorporateCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.results.DataResult;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/corporateCustomer")
public class CorporateCustomersController {

    private CorporateCustomerService corporateCustomerService;

    @Autowired
    public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
        super();
        this.corporateCustomerService = corporateCustomerService;
    }
    @GetMapping("getAll")
    public DataResult<List<CorporateCustomerSearchListDto>> getAll() {
        return this.corporateCustomerService.getAll();
    }
    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        return this.corporateCustomerService.add(createCorporateCustomerRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
        return this.corporateCustomerService.update(updateCorporateCustomerRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
        return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);
    }

}
