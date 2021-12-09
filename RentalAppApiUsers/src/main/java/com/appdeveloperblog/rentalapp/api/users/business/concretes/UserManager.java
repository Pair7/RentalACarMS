package com.appdeveloperblog.rentalapp.api.users.business.concretes;

import com.appdeveloperblog.rentalapp.api.users.business.abstracts.UserService;
import com.appdeveloperblog.rentalapp.api.users.business.constants.Messages;
import com.appdeveloperblog.rentalapp.api.users.business.dtos.UserSearchListDto;
import com.appdeveloperblog.rentalapp.api.users.business.request.user.LoginUserRequest;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.business.BusinessRules;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.mapping.ModelMapperService;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.results.*;
import com.appdeveloperblog.rentalapp.api.users.dataAccess.UserDao;
import com.appdeveloperblog.rentalapp.api.users.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {

    private UserDao userDao;
    private ModelMapperService modelMapperService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserManager(UserDao userDao, ModelMapperService modelMapperService,BCryptPasswordEncoder bCryptPasswordEncoder) {
        super();
        this.userDao = userDao;
        this.modelMapperService = modelMapperService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public Result login(LoginUserRequest loginUserRequest) {
        Result result = BusinessRules.run(checkCredentials(loginUserRequest.getEmail(), loginUserRequest.getPassword()));
        if (result!=null) {
            return result;
        }
        return new SuccessResult(Messages.LOGINSUCCESS);
    }
    private Result checkCredentials(String email,String password) {
        var user =  this.userDao.getByEmail(email);
        if (user == null) {
            return new ErrorResult(Messages.LOGINEMAILERROR);
        }
        var result= bCryptPasswordEncoder.matches(password,user.getEncryptedPassword());
        if (!result) {
            return new ErrorResult(Messages.LOGINPASSWORDERROR+" "+result);
        }
        return new SuccessResult();
    }

    @Override
    public DataResult<UserSearchListDto> getById(int id) {

        User user = this.userDao.getById(id);
        UserSearchListDto userSearchListDto = modelMapperService.forDto().map(user, UserSearchListDto.class);
        return new SuccessDataResult<UserSearchListDto>(userSearchListDto);
    }

    @Override
    public DataResult<UserSearchListDto> getByEmail(String email) {
        User user = this.userDao.getByEmail(email);
        UserSearchListDto userSearchListDto = modelMapperService.forDto().map(user, UserSearchListDto.class);
        return new SuccessDataResult<UserSearchListDto>(userSearchListDto);
    }

    @Override
    public Result checkIfEmailExists(String email) {
        if (this.userDao.existsByEmail(email)) {
            return new ErrorResult(Messages.USERNOTFOUND);
        }
        return new SuccessResult();
    }

    @Override
    public Result checkIfUserExists(int userId) {
        if (!this.userDao.existsById(userId)) {
            return new ErrorResult(Messages.USERNOTFOUND);
        }
        return new SuccessResult();
    }

}
