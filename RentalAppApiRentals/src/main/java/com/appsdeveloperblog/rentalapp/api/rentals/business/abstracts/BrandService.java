package com.appsdeveloperblog.rentalapp.api.rentals.business.abstracts;

import com.appsdeveloperblog.rentalapp.api.rentals.business.dtos.BrandSearchListDto;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.brand.CreateBrandRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.brand.DeleteBrandRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.business.requests.brand.UpdateBrandRequest;
import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.results.DataResult;
import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.results.Result;
import com.appsdeveloperblog.rentalapp.api.rentals.entities.Brand;

import java.util.List;

public interface BrandService {
    DataResult<List<Brand>> getAll();
    Result add(CreateBrandRequest createBrandRequest);
    Result update(UpdateBrandRequest updateBrandRequest);
    Result delete(DeleteBrandRequest deleteBrandRequest);
    Result checkIfBrandExists(int brandId);

}