package com.appsdeveloperblog.rentalapp.api.rentals.business.abstracts;

import com.appsdeveloperblog.rentalapp.api.rentals.business.dtos.CarSearchListDto;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.car.CreateCarRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.car.DeleteCarRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.car.UpdateCarRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.results.DataResult;
import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.results.Result;

import java.util.List;

public interface CarService {
    DataResult<List<CarSearchListDto>> getAll();
    Result add(CreateCarRequest createCarRequest);
    Result update(UpdateCarRequest updateCarRequest);
    Result delete(DeleteCarRequest deleteCarRequest);
    Result checkIfCarExists(int carId);
    Result checkIfExistsColorInCar(int colorId);
    Result checkIfExistsBrandInCar(int brandId);
}
