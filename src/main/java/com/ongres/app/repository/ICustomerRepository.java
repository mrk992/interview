package com.ongres.app.repository;

import java.sql.Timestamp;

public interface ICustomerRepository {

    /**
     * Retrieve the count of customers by country
     * @param country country
     * @return number of countries
     */
    int countCustomersByCountry(String country);

    /**
     * Retrieve the count of customers by country
     * @param city
     * @return number of cities
     */
    int countCustomersByCity(String city);

    /**
     * Check if the customer can rental a DVD
     * @param customerId customer id
     * @param localTimeStamp time stamp of the rental
     * @return bolean if can
     */
    boolean canRental(int customerId, Timestamp localTimeStamp);
}
