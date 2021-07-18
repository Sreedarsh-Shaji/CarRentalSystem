package com.fariz.carrental.services;

import com.fariz.carrental.jparepositories.VehicleRepository;
import com.fariz.carrental.dao.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository repository;

    public void addNewVehicle(Vehicle vehicle)
    {
        repository.save(vehicle);
    }

    public void vehicleDelete(int id) {
        repository.deleteById(id);
    }

    public List<Vehicle> findAllVehicles()
    {
        return repository.findAll();
    }
}
