package com.fariz.carrental.services;

import com.fariz.carrental.dto.AgencyRepository;
import com.fariz.carrental.dto.VehicleRepository;
import com.fariz.carrental.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository repository;

    public void addNewVehicle(Vehicle vehicle)
    {
        repository.save(vehicle);
    }

}
