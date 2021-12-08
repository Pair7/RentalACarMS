package com.appsdeveloperblog.rentalapp.api.rentals.dataAccess;

import com.appsdeveloperblog.rentalapp.api.rentals.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandDao extends JpaRepository<Brand,Integer> {
    boolean existsByBrandName(String brandName);
}
