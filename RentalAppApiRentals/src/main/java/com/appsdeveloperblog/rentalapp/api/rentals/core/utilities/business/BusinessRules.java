package com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.business;

import com.appsdeveloperblog.rentalapp.api.rentals.core.utilities.results.Result;

public class BusinessRules {
    public static Result run(Result ...logics) {
        for (Result logic : logics) {
            if (!logic.isSuccess()) {
                return logic;
            }
        }
        return null;
    }
}