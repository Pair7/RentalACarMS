package com.appdeveloperblog.rentalapp.api.users.business.concretes;

import com.appdeveloperblog.rentalapp.api.users.business.abstracts.CorporateCustomerService;
import com.appdeveloperblog.rentalapp.api.users.business.constants.Messages;
import com.appdeveloperblog.rentalapp.api.users.business.dtos.CorporateCustomerSearchListDto;
import com.appdeveloperblog.rentalapp.api.users.business.request.corporateCustomers.CreateCorporateCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.business.request.corporateCustomers.DeleteCorporateCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.business.request.corporateCustomers.UpdateCorporateCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.business.BusinessRules;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.mapping.ModelMapperService;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.results.*;
import com.appdeveloperblog.rentalapp.api.users.dataAccess.CorporateCustomersDao;
import com.appdeveloperblog.rentalapp.api.users.entities.CorporateCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

    private CorporateCustomersDao corporateCustomersDao;
    private ModelMapperService modelMapperService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CorporateCustomerManager(CorporateCustomersDao corporateCustomersDao,
                                    ModelMapperService modelMapperService,BCryptPasswordEncoder bCryptPasswordEncoder) {
        super();
        this.corporateCustomersDao = corporateCustomersDao;
        this.modelMapperService = modelMapperService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public DataResult<List<CorporateCustomerSearchListDto>> getAll() {
        List<CorporateCustomer> result = this.corporateCustomersDao.findAll();
        List<CorporateCustomerSearchListDto> response = result.stream()
                .map(corporateCustomer -> modelMapperService.forDto()
                        .map(corporateCustomer, CorporateCustomerSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CorporateCustomerSearchListDto>>(response, Messages.CUSTOMERLIST);

    }

    @Override
    public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        Result resultCheck = BusinessRules.run(checkIfCompanyNameExists(createCorporateCustomerRequest.getCompanyName()));
        if (resultCheck != null) {
            return resultCheck;
        }
        createCorporateCustomerRequest.setEncryptedPassword(this.bCryptPasswordEncoder.encode(createCorporateCustomerRequest.getPassword()));
        CorporateCustomer result = modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
        this.corporateCustomersDao.save(result);
        return new SuccessResult(Messages.CUSTOMERADD);
    }

    @Override
    public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
        Result result = BusinessRules.run(checkIfCompanyNameExists(updateCorporateCustomerRequest.getCompanyName()),
                checkIfUserExists(updateCorporateCustomerRequest.getUserId()));
        if (result != null) {
            return result;
        }
        updateCorporateCustomerRequest.setEncryptedPassword(this.bCryptPasswordEncoder.encode(updateCorporateCustomerRequest.getPassword()));
        CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class);
        this.corporateCustomersDao.save(corporateCustomer);
        return new SuccessResult(Messages.CUSTOMERUPDATE);
    }

    @Override
    public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
        Result result = BusinessRules.run(checkIfUserExists(deleteCorporateCustomerRequest.getUserId()));
        if (result != null) {
            return result;
        }

        CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(deleteCorporateCustomerRequest, CorporateCustomer.class);
        this.corporateCustomersDao.delete(corporateCustomer);
        return new SuccessResult(Messages.CUSTOMERDELETE);
    }

    private Result checkIfUserExists(int id) {
        var result = this.corporateCustomersDao.existsById(id);
        if (!result) {
            return new ErrorResult(Messages.CUSTOMERNOTFOUND);
        }
        return new SuccessResult();
    }

    private Result checkIfCompanyNameExists(String companyName) {
        if (this.corporateCustomersDao.existsCorporateCustomerByCompanyName(companyName)) {
            return new ErrorResult(Messages.CUSTOMERISALREADYEXISTS);
        }
        return new SuccessResult();
    }

}

