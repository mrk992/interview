package com.ongres.app.repository;

import com.ongres.app.entities.RentTO;

public interface IRentalRepository {
    /**
     * Save a rent in DB
     * @param rent rent to save
     */
    void saveRent(RentTO rent);
}
