package com.fariz.carrental.controller;

import com.fariz.carrental.messages.Message;
import com.fariz.carrental.model.*;
import com.fariz.carrental.services.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "api/v1/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService service;

    @Autowired
    AgencyService agencyService;
    @Autowired
    OfficesService officesService;
    @Autowired
    TripsServices tripsServices;
    @Autowired
    UserService userService;
    @Autowired
    VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.POST , value = "/viewAllAdmins")
    public List<Admin> loginAdmin()
    {
        return null;
    }

    //Gets the list of all agencies
    @RequestMapping(method = RequestMethod.GET , value = "/viewAllAgencies")
    public List<Agency> getAllAgencies()
    {
        return service.adminSeeAgencyList();
    }

    //Gets the list of all offices
    @RequestMapping(method = RequestMethod.GET , value = "/viewAllOffices")
    public List<Offices> getAllOffices()
    {
        return service.adminSeeOfficesList();
    }

    //Gets the list of all trips
    @RequestMapping(method = RequestMethod.GET , value = "/viewAllTrips")
    public List<Trips> getAllTrips()
    {
        return service.adminSeeTripsList();
    }

    //Gets the list of all trips
    @RequestMapping(method = RequestMethod.GET , value = "/viewAllUser")
    public List<User> getAllUsers()
    {
        return service.adminSeeUserList();
    }

    //Gets the list of all vehicles
    @RequestMapping(method = RequestMethod.GET , value = "/viewAllVehicles")
    public List<Vehicle> getAllVehicles()
    {
        return service.adminSeeVehicleList();
    }

    //Approve an agency
    @RequestMapping(method = RequestMethod.GET , value = "/approveAgency/{id}")
    public Message approveAgency(@PathVariable int id)
    {
        service.approveAgency(id);
        return Message.getMessage();
    }

    //Creates set of admins
    @PostConstruct
    protected void createAdmins()
    {
        try {

            List<Admin> admins = new ArrayList<Admin>();

            admins.add(new Admin(1,"Super Admin","admin@carrentals.com","Admin123",new Date(),new Date()));
            admins.add(new Admin(2,"Anju","Anju@carrentals.com","Anju123",new Date(),new Date()));
            admins.add(new Admin(3,"Sumi","Sumi@carrentals.com","Sumi123",new Date(),new Date()));
            admins.add(new Admin(4,"Sudheesh","sudheesh@carrentals.com","Sudheesh123",new Date(),new Date()));
            admins.add(new Admin(5,"Sreedarsh","sreedarsh@carrentals.com","sreedarsh123",new Date(),new Date()));

            admins.forEach(temp -> service.save(temp));//lambda

            logger.info("Added 5 admins using @PostConstruct annotation!!!");

        }
        catch(Exception exception)
        {

        }
    }

}
