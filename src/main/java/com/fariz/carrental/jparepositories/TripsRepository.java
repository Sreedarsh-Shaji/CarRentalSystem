package com.fariz.carrental.jparepositories;

import com.fariz.carrental.dao.Trips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TripsRepository extends JpaRepository<Trips,Integer> {

}
