package com.appdeveloperblog.rentalapp.api.users.dataAccess;

import com.appdeveloperblog.rentalapp.api.users.entities.CorporateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateCustomersDao extends JpaRepository<CorporateCustomer,Integer> {
    boolean existsCorporateCustomerByCompanyName(String companyName);
}
