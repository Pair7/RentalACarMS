package com.appsdeveloperblog.rentalapp.api.rentals.dataAccess;

import com.appsdeveloperblog.rentalapp.api.rentals.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarDao extends JpaRepository<Car,Integer> {
    List<Car> getByColor_ColorId(int colorId);
    List<Car> getByBrand_BrandId(int brandId);

}
