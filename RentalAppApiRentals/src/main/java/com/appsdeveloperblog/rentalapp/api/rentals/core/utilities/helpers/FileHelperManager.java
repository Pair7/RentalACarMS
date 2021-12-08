package com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.helpers;

import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.results.Result;
import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileHelperManager implements FileHelper {

    @Override
    public Result deleteImage(String imagePath) {
        if (!imagePath.isEmpty()) {
            File file = new File(imagePath);
            file.delete();
        }
        return new SuccessResult();
    }

}
