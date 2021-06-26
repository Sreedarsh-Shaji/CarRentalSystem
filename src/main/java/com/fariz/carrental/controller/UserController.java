package com.fariz.carrental.controller;

import com.fariz.carrental.dao.Admin;
import com.fariz.carrental.dao.User;
import com.fariz.carrental.services.*;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

            users.add(new User(1,"meekha","meekha@carrentalsals.com","meekha123","9836546210","License12/A/1","true",new Date(),new Date()));
            users.add(new User(2,"Deepthi","deepthi@carrentals.com","deepthi123","9866543210","License12/A/1","true",new Date(),new Date()));
            users.add(new User(3,"Meenu","Meenu@carrentals.com","Meenu123","9876543880","License12/A/1","true",new Date(),new Date()));
            users.add(new User(4,"Basheer","basheer@carrentals.com","bashi123","9876540010","License12/A/1","true",new Date(),new Date()));
            users.add(new User(5,"Sruthi","sruthi@carrentals.com","sruthi123","9006543210","License12/A/1","true",new Date(),new Date()));

            users.forEach(temp -> userService.addNewUser(temp));//lambda

            logger.info("Added 5 Users using @PostConstruct annotation!!!");

        }
        catch(Exception exception)
        {

        }
    }
    @ApiOperation( value = "User authentication")
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET , value = "/userLogin/{username}/{password}")
    public String userLogIn(@PathVariable String username, @PathVariable String password)
    {
        List<User> users = userService.getAllUsers();
        User retUser = null;
        String flag="";
        for (User temp : users)
        {
            if(temp.getEmail().equals(username)
                    && temp.getPassword().equals(password))
            {
                retUser = temp;
                flag="VALID CREDENTIAL";
            }
            else
            { flag="INVALID CREDENTIAL";}


        }
    return flag;
    }
}
