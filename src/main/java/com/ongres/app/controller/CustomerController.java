package com.ongres.app.controller;

import com.ongres.app.entities.CountCustomersTO;
import com.ongres.app.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    @Qualifier("CustomerRepository")
    private ICustomerRepository customerRepository;

    @RequestMapping("/count-country")
    public CountCustomersTO countUsersByCountry(@RequestParam(value = "country") String country) {
        CountCustomersTO customers = new CountCustomersTO();
        if (!country.isEmpty()) {
            //customerRepository = new CustomerRepository();
            customers.setCustormers(customerRepository.countCustomersByCountry(country));

        } else {
            customers.setCustormers(0);
        }
        return customers;

    }

    @RequestMapping("/count-city")
    public CountCustomersTO countUsersByCity(@RequestParam(value = "city") String city) {
        CountCustomersTO customers = new CountCustomersTO();
        if (!city.isEmpty()) {
            //customerRepository = new CustomerRepository();
            customers.setCustormers(customerRepository.countCustomersByCity(city));

        } else {
            customers.setCustormers(0);
        }
        return customers;
    }
}
