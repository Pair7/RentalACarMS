package com.appsdeveloperblog.rentalapp.api.rentals.business.concretes;

import com.appsdeveloperblog.rentalapp.api.rentals.business.abstracts.BrandService;
import com.appsdeveloperblog.rentalapp.api.rentals.business.abstracts.CarService;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.brand.CreateBrandRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.brand.DeleteBrandRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.brand.UpdateBrandRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.business.BusinessRules;
import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.mapping.ModelMapperService;
import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.results.*;
import com.appsdeveloperblog.rentalapp.api.rentals.dataAccess.BrandDao;
import com.appsdeveloperblog.rentalapp.api.rentals.entities.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandManager implements BrandService {
    private BrandDao brandDao;
    private ModelMapperService modelMapperService;
    private CarService carService;

    @Autowired
    private BrandManager(BrandDao brandDao, ModelMapperService modelMapperService, @Lazy CarService carService) {
        super();
        this.brandDao = brandDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
    }
    @Override
    public DataResult<List<Brand>> getAll() {
        return new SuccessDataResult<List<Brand>>(this.brandDao.findAll());
    }



    @Override
    public Result add(CreateBrandRequest createBrandRequest) {
        Result result = BusinessRules.run(this.checkIfBrandNameExists(createBrandRequest.getBrandName()));
        if (result != null) {
            return result;
        }
        Brand brand2 = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
        this.brandDao.save(brand2);
        return new SuccessResult();
    }

    @Override
    public Result update(UpdateBrandRequest updateBrandRequest) {
        Result result = BusinessRules.run(checkIfBrandNameExists(updateBrandRequest.getBrandName()),
                checkIfBrandExists(updateBrandRequest.getBrandId()));
        if (result != null) {
            return result;
        }
        Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
        this.brandDao.save(brand);
        return new SuccessResult();
    }

    @Override
    public Result delete(DeleteBrandRequest deleteBrandRequest) {
        Result result = BusinessRules.run(checkIfBrandExists(deleteBrandRequest.getBrandId())
                , checkIfIsThereCarOfThisBrand(deleteBrandRequest.getBrandId()));
        if (result != null) {
            return result;
        }
        Brand brand = this.modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
        this.brandDao.delete(brand);
        return new SuccessResult();
    }
    @Override
    public Result checkIfBrandExists(int brandId) {
        if (!this.brandDao.existsById(brandId)) {
            return new ErrorResult("Brand Not Found");
        }
        return new SuccessResult();
    }
    private Result checkIfBrandNameExists(String brandName) {
        if (this.brandDao.existsByBrandName(brandName)) {
            return new ErrorResult("BRAND NAME ERROR");
        }
        return new SuccessResult();
    }
    private Result checkIfIsThereCarOfThisBrand(int brandId) {
        if (this.carService.checkIfExistsBrandInCar(brandId).isSuccess()) {
            return new ErrorResult("Messages.BRANDDELETEERROR");
        }
        return new SuccessResult();
    }

}
