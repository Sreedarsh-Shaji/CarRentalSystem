package com.fariz.carrental.dto;

import com.fariz.carrental.model.Agency;
import com.fariz.carrental.model.Offices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficesRepository extends JpaRepository<Offices,Integer> {

}
