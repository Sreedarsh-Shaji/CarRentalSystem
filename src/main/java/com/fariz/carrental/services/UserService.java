package com.fariz.carrental.services;

import com.fariz.carrental.dao.Admin;
import com.fariz.carrental.dao.Trips;
import com.fariz.carrental.jparepositories.UserRepository;
import com.fariz.carrental.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public void addNewAgency(User temp)
    {
        repository.save(temp);
    }

    //Return a list of all users
    public List<User> getAllUsers()
    {
        return repository.findAll();
    }


    public void deleteUser(int id) {
        User users = repository.findById(id).get();
        repository.save(users);
    }

    public void signUpUser(User user) {

        repository.save(user);

    }
}
