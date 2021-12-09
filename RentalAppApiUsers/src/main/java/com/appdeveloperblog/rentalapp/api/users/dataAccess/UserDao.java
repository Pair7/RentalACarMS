package com.appdeveloperblog.rentalapp.api.users.dataAccess;

import com.appdeveloperblog.rentalapp.api.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {

    User getByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsById(int userId);
}
