package com.fariz.carrental.dto;

import com.fariz.carrental.model.Admin;
import com.fariz.carrental.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgencyRepository extends JpaRepository<Agency,Integer> {

    List<Agency> findAllByEmail(String email);
}
