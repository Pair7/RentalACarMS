package com.appdeveloperblog.rentalapp.api.users.business.concretes;

import com.appdeveloperblog.rentalapp.api.users.business.abstracts.UserService;
import com.appdeveloperblog.rentalapp.api.users.business.constants.Messages;
import com.appdeveloperblog.rentalapp.api.users.business.dtos.UserSearchListDto;
import com.appdeveloperblog.rentalapp.api.users.business.request.user.LoginUserRequest;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.business.BusinessRules;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.mapping.ModelMapperService;
import com.appdeveloperblog.rentalapp.api.users.core.utilities.results.*;
import com.appdeveloperblog.rentalapp.api.users.dataAccess.UserDao;
import com.appdeveloperblog.rentalapp.api.users.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
    public DataResult<UserSearchListDto> getById(int id) {

        UserEntity userEntity = this.userDao.getById(id);
        UserSearchListDto userSearchListDto = modelMapperService.forDto().map(userEntity, UserSearchListDto.class);
        return new SuccessDataResult<UserSearchListDto>(userSearchListDto);
    }

    @Override
    public DataResult<UserSearchListDto> getByEmail(String email) {
        UserEntity userEntity = this.userDao.getByEmail(email);
        UserSearchListDto userSearchListDto = modelMapperService.forDto().map(userEntity, UserSearchListDto.class);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userDao.findByEmail(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(),true,true,true,true,new ArrayList<>());
    }
    public UserSearchListDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = this.userDao.findByEmail(email);
        if(userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        UserSearchListDto userSearchListDto = modelMapperService.forDto().map(userEntity, UserSearchListDto.class);

        return userSearchListDto;
    }
}
