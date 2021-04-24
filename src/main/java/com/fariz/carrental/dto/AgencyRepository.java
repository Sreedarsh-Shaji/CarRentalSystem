package com.fariz.carrental.dto;

import com.fariz.carrental.model.Admin;
import com.fariz.carrental.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyRepository extends JpaRepository<Agency,Integer> {

}
