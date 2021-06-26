package com.fariz.carrental.jparepositories;

import com.fariz.carrental.dao.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {

}
