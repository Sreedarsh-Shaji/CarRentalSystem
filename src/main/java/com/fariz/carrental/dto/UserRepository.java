package com.fariz.carrental.dto;

import com.fariz.carrental.model.Trips;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Trips,Integer> {

}
