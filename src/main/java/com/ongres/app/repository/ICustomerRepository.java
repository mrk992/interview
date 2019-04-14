package com.ongres.app.repository;

public interface ICustomerRepository {

    int countCustomersByCountry(String country);

    int countCustomersByCity(String city);
}
