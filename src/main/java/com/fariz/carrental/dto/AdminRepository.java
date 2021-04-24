package com.fariz.carrental.dto;

import com.fariz.carrental.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {

}
