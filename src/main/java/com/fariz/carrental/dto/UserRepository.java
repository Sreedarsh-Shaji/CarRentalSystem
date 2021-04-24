package com.fariz.carrental.dto;

import com.fariz.carrental.model.Trips;
import com.fariz.carrental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
