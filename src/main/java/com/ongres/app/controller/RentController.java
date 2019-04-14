package com.ongres.app.controller;

import com.ongres.app.entities.FilmsByActorTO;
import com.ongres.app.entities.RentDVDJSON;
import com.ongres.app.entities.RentTO;
import com.ongres.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RentController {
    @Autowired
    @Qualifier("InventoryRepository")
    private IInventoryRepository inventoryRepository;

    @Autowired
    @Qualifier("CustomerRepository")
    private ICustomerRepository customerRepository;

    @Autowired
    @Qualifier("StaffRepository")
    private IStaffRepository staffRepository;

    @Autowired
    @Qualifier("RentalRepository")
    private IRentalRepository rentalRepository;

    @RequestMapping("/rent")
    @PostMapping
    public ResponseEntity rentADVD(@RequestBody RentDVDJSON rent) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int id = inventoryRepository.getInventoryIdByName(rent.getTitle());
        String names[] = rent.getStaffName().split(" ");
        if (names.length != 2) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        int staffId = 0;
        staffId = staffRepository.getStaffByName(names[0], names[1]);

        if (customerRepository.canRental(rent.getCustomerId(), timestamp) &&
                /*inventoryRepository.isAvailableInInventory(id) && */staffId != 0) {
            RentTO rentTO = new RentTO();
            rentTO.setCustomerId(rent.getCustomerId());
            rentTO.setInventoryId(id);
            rentTO.setStaffId(staffId);
            rentTO.setTimestamp(timestamp);
            rentalRepository.saveRent(rentTO);

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping("/return")
    @PostMapping
    public ResponseEntity returnADVD(@RequestBody RentDVDJSON rent) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
