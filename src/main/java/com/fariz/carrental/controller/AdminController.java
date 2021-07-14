package com.fariz.carrental.controller;

import com.fariz.carrental.messages.Message;
import com.fariz.carrental.dao.*;
import com.fariz.carrental.services.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "api/v1/admin")
@Api( value = "Admin Controller")
@CrossOrigin(origins = "http://localhost:3010", maxAge = 3600)
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

    //Gets the list of all agencies
    @ApiOperation(value = "Get all agencies")
    @RequestMapping(method = RequestMethod.GET , value = "/viewAllAgencies")
    public List<Agency> getAllAgencies()
    {
        return service.adminSeeAgencyList();
    }

    //Gets the list of all offices
    @RequestMapping(method = RequestMethod.GET , value = "/viewAllOffices")
    public List<Office> getAllOffices()
    {
        return service.adminSeeOfficesList();
    }

    //Gets the list of all trips
    @RequestMapping(method = RequestMethod.GET , value = "/viewAllTrips")
    public Map<String,Object> getAllTrips()
    {
        Map<String,Object> tripDetails = new HashMap<String, Object>();

        List<Trips> trips = service.adminSeeTripsList();

        for (Trips temp:trips)
        {
            Map<String,Object> trip_data =  new HashMap<String, Object>();

            trip_data.put("trip_details",temp);
            trip_data.put("start_location",officesService.getOffice(temp.getPickupOfficeLocation()));
            trip_data.put("end_location",officesService.getOffice(temp.getPickupOfficeLocation()));

            tripDetails.put(temp.getTripId()+"",trip_data);
        }

        return tripDetails;
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
    @RequestMapping(method = RequestMethod.PUT , value = "/approveAgency/{id}")
    public Message approveAgency(@PathVariable int id)
    {
        service.approveAgency(id);
        return Message.getMessage();
    }

    //updates user status

    @ApiOperation( value = "Admin authentication")
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET , value = "/adminLogin/{username}/{password}")
    public Admin adminLogIn(@PathVariable String username,@PathVariable String password)
    {
        List<Admin> admins = service.getAllAdmins();
        Admin retAdmin = null;

        for (Admin temp : admins)
        {
            if(temp.getAdminEmail().equals(username)
                    && temp.getAdminPassword().equals(password))
            {
                retAdmin = temp;
            }
        }
        return retAdmin;
    }
    //delete agency
    @RequestMapping(method = RequestMethod.DELETE,value = "/agency/{id}")
    private void deleteAgency(@PathVariable int id){
        agencyService.deleteAgency(id);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/user/{id}")
    private void deleteUsers(@PathVariable int id){
        userService.deleteUser(id);
    }

    @RequestMapping(method =  RequestMethod.DELETE,value = "/trip/{id}")
    private void tripDelete(@PathVariable int id){
        tripsServices.tripDelete(id);
    }

    @RequestMapping(method =  RequestMethod.DELETE,value = "/officers/{id}")
    private void officeDelete(@PathVariable int id){
        officesService.officeDelete(id);
    }


    @RequestMapping(method =  RequestMethod.DELETE,value = "/vehicle/{id}")
    private void vehicleDelete(@PathVariable int id){
        vehicleService.vehicleDelete(id);
    }


    //Creates set of admins
    @PostConstruct
    protected void createAdmins()
    {
        try {

            List<Admin> admins = new ArrayList<Admin>();

            admins.add(new Admin(1 ,"Super Admin","admin@carrentals.com","Admin123",new Date(),new Date()));
            admins.add(new Admin(2,"Anju","Anju@carrentals.com","Anju123",new Date(),new Date()));
            admins.add(new Admin(3,"Sumi","Sumi@carrentals.com","Sumi123",new Date(),new Date()));
            admins.add(new Admin(4,"Sudheesh","sudheesh@carrentals.com","Sudheesh123",new Date(),new Date()));
            admins.add(new Admin(4,"Sreedarsh","sreedarsh@carrentals.com","sreedarsh123",new Date(),new Date()));

            admins.forEach(temp -> service.save(temp));//lambda

            logger.info("Added 5 admins using @PostConstruct annotation!!!");

        }
        catch(Exception exception)
        {

        }
    }

}
