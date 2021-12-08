package com.appsdeveloperblog.rentalapp.api.rentals.business.concretes;

import com.appsdeveloperblog.rentalapp.api.rentals.business.abstracts.CarService;
import com.appsdeveloperblog.rentalapp.api.rentals.business.abstracts.ColorService;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.color.CreateColorRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.color.DeleteColorRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.color.UpdateColorRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.business.BusinessRules;
import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.mapping.ModelMapperService;
import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.results.*;
import com.appsdeveloperblog.rentalapp.api.rentals.dataAccess.ColorDao;
import com.appsdeveloperblog.rentalapp.api.rentals.entities.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorManager implements ColorService {

    private ColorDao colorDao;
    private ModelMapperService modelMapperService;
    private CarService carService;

    @Autowired
    private ColorManager(ColorDao colorDao, ModelMapperService modelMapperService, @Lazy CarService carService) {
        super();
        this.colorDao = colorDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
    }
    @Override
    public DataResult<List<Color>> getAll() {
        return new SuccessDataResult<List<Color>>(this.colorDao.findAll());
    }

    @Override
    public Result add(CreateColorRequest createColorRequest) {
        Result result = BusinessRules.run(checkIfColorNameExists(createColorRequest.getColorName()));
        if (result != null) {
            return result;
        }
        Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
        this.colorDao.save(color);
        return new SuccessResult();
    }

    @Override
    public Result update(UpdateColorRequest updateColorRequest) {
        Result result = BusinessRules.run(checkIfColorNameExists(updateColorRequest.getColorName()),
                checkIfColorExists(updateColorRequest.getColorId()));
        if (result != null) {
            return result;
        }
        Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
        this.colorDao.save(color);
        return new SuccessResult();
    }

    @Override
    public Result delete(DeleteColorRequest deleteColorRequest) {
        Result result = BusinessRules.run(checkIfColorExists(deleteColorRequest.getColorId())
                , checkIfExistsColorInCar(deleteColorRequest.getColorId()));
        if (result != null) {
            return result;
        }
        Color color = this.modelMapperService.forRequest().map(deleteColorRequest, Color.class);
        this.colorDao.delete(color);
        return new SuccessResult();
    }
    private Result checkIfExistsColorInCar(int colorId) {
        if (this.carService.checkIfExistsColorInCar(colorId).isSuccess()) {
            return new ErrorResult("COLOR DELETE ERROR");
        }
        return new SuccessResult();
    }
    @Override
    public Result checkIfColorExists(int colorId) {
        if (!this.colorDao.existsById(colorId)) {
            return new ErrorResult("COLOR NOT FOUND");
        }
        return new SuccessResult();
    }

    private Result checkIfColorNameExists(String colorName) {
        if (this.colorDao.existsByColorName(colorName)) {
            return new ErrorResult("COLOR NAME ERROR");
        }
        return new SuccessResult();
    }

}
