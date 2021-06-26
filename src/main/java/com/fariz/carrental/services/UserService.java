package com.fariz.carrental.services;

import com.fariz.carrental.dao.Admin;
import com.fariz.carrental.jparepositories.UserRepository;
import com.fariz.carrental.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public void addNewUser(User temp)
    {
        repository.save(temp);
    }
    public List<User> getAllUsers()
    {
        return repository.findAll();
    }

}
