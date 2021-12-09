package com.appdeveloperblog.rentalapp.api.users.dataAccess;

import com.appdeveloperblog.rentalapp.api.users.entities.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualCustomerDao extends JpaRepository<IndividualCustomer,Integer> {

}
