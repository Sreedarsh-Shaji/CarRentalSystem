package com.fariz.carrental.dto;

import com.fariz.carrental.model.Trips;
import com.fariz.carrental.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {

}
