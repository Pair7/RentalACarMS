package com.appsdeveloperblog.rentalapp.api.rentals.controllers;

import com.appsdeveloperblog.rentalapp.api.rentals.business.abstracts.CarService;
import com.appsdeveloperblog.rentalapp.api.rentals.business.dtos.CarSearchListDto;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.car.CreateCarRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.car.DeleteCarRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.car.UpdateCarRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.results.DataResult;
import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarsController {
    private CarService carService;

    @Autowired
    private CarsController(CarService carService) {
        super();
        this.carService = carService;
    }


    @GetMapping("getAll")
    public DataResult<List<CarSearchListDto>> getAll() {
        return carService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
        return this.carService.add(createCarRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
        return this.carService.update(updateCarRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteCarRequest deleteCarRequest) {
        return this.carService.delete(deleteCarRequest);
    }
}
