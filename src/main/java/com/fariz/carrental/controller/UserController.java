package com.fariz.carrental.controller;

import com.fariz.carrental.model.Agency;
import com.fariz.carrental.model.Office;
import com.fariz.carrental.model.User;
import com.fariz.carrental.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired AdminService adminService;
    @Autowired AgencyService agencyService;
    @Autowired OfficesService officesService;
    @Autowired TripsServices tripsServices;
    @Autowired UserService userService;
    @Autowired VehicleService vehicleService;

    @GetMapping("/hello")
    protected String hello()
    {
        return "Say hai!!!";
    }


    //Creates set of users
    @PostConstruct
    protected void createDummyUsers()
    {
        try {

            List<User> users = new ArrayList<User>();

            users.add(new User(1,"Aneesh","admin@carrentals.com","Admin123","9876543210","License12/A/1","false",new Date(),new Date()));
            users.add(new User(2,"Anju","Anju@carrentals.com","Admin123","9876543210","License12/A/1","false",new Date(),new Date()));
            users.add(new User(3,"Sumi","Sumi@carrentals.com","Admin123","9876543210","License12/A/1","false",new Date(),new Date()));
            users.add(new User(4,"Sudheesh","sudheesh@carrentals.com","Admin123","9876543210","License12/A/1","false",new Date(),new Date()));
            users.add(new User(5,"Sreedarsh","sreedarsh@carrentals.com","Admin123","9876543210","License12/A/1","false",new Date(),new Date()));

            users.forEach(temp -> userService.addNewAgency(temp));//lambda

            logger.info("Added 5 Users using @PostConstruct annotation!!!");

        }
        catch(Exception exception)
        {

        }
    }
}
