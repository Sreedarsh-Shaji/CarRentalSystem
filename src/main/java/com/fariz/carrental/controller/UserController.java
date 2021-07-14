package com.fariz.carrental.controller;

import com.fariz.carrental.dao.Admin;
import com.fariz.carrental.dao.Trips;
import com.fariz.carrental.dao.User;
import com.fariz.carrental.services.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@Api( value = "User Controller")
@CrossOrigin(origins = "http://localhost:3010", maxAge = 3600)
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


    @RequestMapping(method = RequestMethod.POST, value = "/usersignup")
    private void addAdmins(@RequestBody User user){
        userService.signUpUser(user);
    }



    @ApiOperation( value = "user authentication")
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET , value = "/userlogin/{username}/{password}")
    public User adminLogIn(@PathVariable String username,@PathVariable String password)
    {
        List<User> users = userService.getAllUsers();
        User retUser = null;

        for (User temp : users)
        {
            if(temp.getEmail().equals(username)
                    && temp.getPassword().equals(password))
            {
                retUser = temp;
            }
        }
        return retUser;
    }

    @GetMapping("/user_see_all_trips")
    public List<Trips> viewAllTrips()
    {
        return tripsServices.seeAllTrips();
    }

    //Creates set of users
    @PostConstruct
    protected void createDummyUsers()
    {
        try {

            List<User> users = new ArrayList<User>();

            users.add(new User(1,"Aneesh","admin@carrentals.com","Admin123","9876543210","License12/A/1","true",new Date(),new Date()));
            users.add(new User(2,"Anju","Anju@carrentals.com","Admin123","9876543210","License12/A/1","true",new Date(),new Date()));
            users.add(new User(2,"Sumi","Sumi@carrentals.com","Admin123","9876543210","License12/A/1","true",new Date(),new Date()));
            users.add(new User(3,"Sudheesh","sudheesh@carrentals.com","Admin123","9876543210","License12/A/1","true",new Date(),new Date()));
            users.add(new User(4,"Sreedarsh","sreedarsh@carrentals.com","Admin123","9876543210","License12/A/1","true",new Date(),new Date()));

            users.forEach(temp -> userService.addNewAgency(temp));//lambda

            logger.info("Added 5 Users using @PostConstruct annotation!!!");

        }
        catch(Exception exception)
        {

        }
    }
}
