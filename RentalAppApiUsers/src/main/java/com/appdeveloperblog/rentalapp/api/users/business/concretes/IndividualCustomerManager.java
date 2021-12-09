package com.appdeveloperblog.rentalapp.api.users.business.concretes;

import com.appdeveloperblog.rentalapp.api.users.business.abstracts.IndividualCustomerService;
import com.appdeveloperblog.rentalapp.api.users.business.constants.Messages;
import com.appdeveloperblog.rentalapp.api.users.business.dtos.IndividualCustomerSearchListDto;
import com.appdeveloperblog.rentalapp.api.users.business.request.individualCustomers.CreateIndividualCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.business.request.individualCustomers.DeleteIndividualCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.business.request.individualCustomers.UpdateIndividualCustomerRequest;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.business.BusinessRules;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.mapping.ModelMapperService;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.results.*;
import com.appdeveloperblog.rentalapp.api.users.dataAccess.IndividualCustomerDao;
import com.appdeveloperblog.rentalapp.api.users.entities.IndividualCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

    private IndividualCustomerDao individualCustomerDao;
    private ModelMapperService modelMapperService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private IndividualCustomerManager(IndividualCustomerDao individualCustomerDao,
                                      ModelMapperService modelMapperService,BCryptPasswordEncoder bCryptPasswordEncoder) {
        super();
        this.individualCustomerDao = individualCustomerDao;
        this.modelMapperService = modelMapperService;
        this.bCryptPasswordEncoder= bCryptPasswordEncoder;
    }

    @Override
    public DataResult<List<IndividualCustomerSearchListDto>> getAll() {
        List<IndividualCustomer> result = this.individualCustomerDao.findAll();
        List<IndividualCustomerSearchListDto> response = result.stream()
                .map(customerIndividual -> modelMapperService.forDto()
                        .map(customerIndividual, IndividualCustomerSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<IndividualCustomerSearchListDto>>(response, Messages.CUSTOMERLIST);
    }

    @Override
    public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        createIndividualCustomerRequest.setEncryptedPassword(this.bCryptPasswordEncoder.encode(createIndividualCustomerRequest.getPassword()));
        IndividualCustomer result = modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
        this.individualCustomerDao.save(result);
        return new SuccessResult(Messages.CUSTOMERADD);
    }

    @Override
    public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
        Result result = BusinessRules.run(checkIfUserExists(updateIndividualCustomerRequest.getUserId()));
        if (result != null) {
            return result;
        }
        updateIndividualCustomerRequest.setEncryptedPassword(this.bCryptPasswordEncoder.encode(updateIndividualCustomerRequest.getPassword()));
        IndividualCustomer individualCustomerResult = modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);
        this.individualCustomerDao.save(individualCustomerResult);
        return new SuccessResult(Messages.CUSTOMERUPDATE);
    }

    @Override
    public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
        Result result = BusinessRules.run(checkIfUserExists(deleteIndividualCustomerRequest.getUserId()));
        if (result != null) {
            return result;
        }

        IndividualCustomer individualCustomerResult = modelMapperService.forRequest().map(deleteIndividualCustomerRequest, IndividualCustomer.class);
        this.individualCustomerDao.delete(individualCustomerResult);
        return new SuccessResult(Messages.CUSTOMERDELETE);
    }

    private Result checkIfUserExists(int id) {
        var result = this.individualCustomerDao.existsById(id);
        if (!result) {
            return new ErrorResult(Messages.USERNOTFOUND);
        }
        return new SuccessResult(Messages.USERFOUND);
    }
}
