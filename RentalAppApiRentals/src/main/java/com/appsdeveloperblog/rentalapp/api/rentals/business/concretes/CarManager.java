package com.appsdeveloperblog.rentalapp.api.rentals.business.concretes;

import com.appsdeveloperblog.rentalapp.api.rentals.business.abstracts.BrandService;
import com.appsdeveloperblog.rentalapp.api.rentals.business.abstracts.CarService;
import com.appsdeveloperblog.rentalapp.api.rentals.business.abstracts.ColorService;
import com.appsdeveloperblog.rentalapp.api.rentals.business.dtos.CarSearchListDto;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.car.CreateCarRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.car.DeleteCarRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.car.UpdateCarRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.business.BusinessRules;
import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.mapping.ModelMapperService;
import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.results.*;
import com.appsdeveloperblog.rentalapp.api.rentals.dataAccess.CarDao;
import com.appsdeveloperblog.rentalapp.api.rentals.entities.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarManager implements CarService {

    private CarDao carDao;
    private ModelMapperService modelMapperService;
    private BrandService brandService;
    private ColorService colorService;

    @Autowired
    public CarManager(CarDao carDao, ModelMapperService modelMapperService,BrandService brandService,ColorService colorService) {
        this.carDao = carDao;
        this.modelMapperService = modelMapperService;
        this.brandService=brandService;
        this.colorService=colorService;
    }


    @Override
    public DataResult<List<CarSearchListDto>> getAll() {
        List<Car> result = this.carDao.findAll();
        List<CarSearchListDto> response = result.stream()
                .map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CarSearchListDto>>(response);
    }


    @Override
    public Result add(CreateCarRequest createCarRequest) {
        Result result = BusinessRules
                .run(checkIfBrandExists(createCarRequest.getBrandId()),checkIfColorExists(createCarRequest.getColorId()));
        if (result != null) {
            return result;
        }
        Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);

        this.carDao.save(car);
        return new SuccessResult();
    }

    @Override
    public Result update(UpdateCarRequest updateCarRequest) {
        Result result = BusinessRules.run(
                checkIfBrandExists(updateCarRequest.getBrandId())
                , checkIfColorExists(updateCarRequest.getColorId()),
                checkIfCarExists(updateCarRequest.getCarId()));
        if (result != null) {
            return result;
        }
        Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
        carDao.save(car);
        return new SuccessResult();
    }

    @Override
    public Result delete(DeleteCarRequest deleteCarRequest) {
        Result result = BusinessRules.run(checkIfCarExists(deleteCarRequest.getCarId()));
        if (result != null) {
            return result;
        }
        Car car = modelMapperService.forRequest().map(deleteCarRequest, Car.class);
        carDao.delete(car);
        return new SuccessResult();
    }

    private Result checkIfColorExists(int colorId) {
        if (!this.colorService.checkIfColorExists(colorId).isSuccess()) {
            return new ErrorResult("COLOR NOT FOUND");
        }
        return new SuccessResult();
    }
    private Result checkIfBrandExists(int brandId) {
        if (!this.brandService.checkIfBrandExists(brandId).isSuccess()) {
            return new ErrorResult("brand not found");
        }
        return new SuccessResult();
    }

    @Override
    public Result checkIfCarExists(int carId) {
        if (!this.carDao.existsById(carId)) {
            return new ErrorResult("CAR NOT FOUND");
        }
        return new SuccessResult();
    }
    @Override
    public Result checkIfExistsColorInCar(int colorId) {
        if (!this.carDao.getByColor_ColorId(colorId).isEmpty()) {
            return new SuccessResult("Messages.COLORDELETEERROR");
        }
        return new ErrorResult();
    }
    @Override
    public Result checkIfExistsBrandInCar(int brandId) {
        if (!this.carDao.getByBrand_BrandId(brandId).isEmpty()) {
            return new SuccessResult("Messages.BRANDDELETEERROR");
        }
        return new ErrorResult();
    }


}
