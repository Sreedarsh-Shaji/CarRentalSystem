package com.fariz.carrental.services;

import com.fariz.carrental.dto.AgencyRepository;
import com.fariz.carrental.dto.UserRepository;
import com.fariz.carrental.model.Agency;
import com.fariz.carrental.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public void addNewAgency(User temp)
    {
        repository.save(temp);
    }

}
