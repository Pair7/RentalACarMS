package com.appdeveloperblog.rentalapp.api.users.dataAccess;

import com.appdeveloperblog.rentalapp.api.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity,Integer> {

    UserEntity getByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsById(int userId);
    UserEntity findByEmail(String email);
}
