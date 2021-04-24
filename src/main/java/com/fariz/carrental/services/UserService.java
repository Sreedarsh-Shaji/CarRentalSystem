package com.fariz.carrental.services;

import com.fariz.carrental.dto.AgencyRepository;
import com.fariz.carrental.dto.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

}
