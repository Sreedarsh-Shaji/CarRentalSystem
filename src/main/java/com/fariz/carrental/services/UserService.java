package com.fariz.carrental.services;

import com.fariz.carrental.jparepositories.UserRepository;
import com.fariz.carrental.dao.User;
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
