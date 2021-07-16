package com.fariz.carrental.controller;

import com.fariz.carrental.dao.*;
import com.fariz.carrental.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;

@RestController
@RequestMapping(value = "api/v1/trips")
@CrossOrigin(origins = "http://localhost:3010", maxAge = 3600)
public class TripsController {

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

    @DeleteMapping("/delete/{id}")
    protected boolean deleteTrip(@PathVariable int id){
        try{
            tripsServices.tripDelete(id);
            return true;
        }
        catch (Exception ex)
        {
            logger.info("[Sreedarsh Written] : "+ ex);
            return false;
        }
    }

    @GetMapping("/see-all-offices")
    public Set<String> getStartingOffices()
    {
        List<Office> offices = officesService.getAllOffices();
        Set<String> locations = new HashSet<String>();

        for (Office temp: offices) {
            locations.add(temp.getOfficeAddress());
        }

        return locations;
    }

    @GetMapping("/see-all-offices/{officeLoc}")
    public Set<Agency> getAgencyBasedOnOfficeName(@PathVariable String officeLoc)
    {
        List<Office> offices = officesService.getAllOffices();
        Set<Agency> agencies = new HashSet<Agency>();

        for (Office temp: offices) {
            if(temp.getOfficeAddress().equals(officeLoc)){
                agencies.add(temp.getAgency());
            }
        }

        return agencies;
    }

    @GetMapping("/see-office-from-agency/{agencyName}")
    public Set<Office> getOfficeBasedOnAgency(@PathVariable int agencyName)
    {
        List<Office> offices = officesService.getAllOffices();
        Set<Office> resOffices = new HashSet<Office>();

        for (Office temp: offices) {
            if(temp.getAgency().getAgencyId() == (agencyName)){
                resOffices.add(temp);
            }
        }

        return resOffices;
    }



    //Creates set of trips
    @PostConstruct
    protected void createDummyTrips()
    {
        try {

            List<Trips> trips = new ArrayList<Trips>();

            Agency agency1 = (new Agency(7,"Sumi","Sumi@carrentals.com","Sumi123","9876543210",new Date(),new Date(),true));
            Agency agency2 = (new Agency(8,"Sudheesh","sudheesh@carrentals.com","Sudheesh123","9876543210",new Date(),new Date(),true));

            Office office1 = new Office(1,agency1,"Kollam","9876543210","9876543210","mail@gmail.com","asd123","8.9125415,76.6297183",new Date());
            Office office2 = new Office(2,agency2,"Trivandrum","9876543210","9876543210","mail@gmail.com","asd123","8.9125415,76.6297183",new Date());

            User user1 = (new User(1,"Aneesh","admin@carrentals.com","Admin123","9876543210","License12/A/1","false",new Date(),new Date()));
            User user2 = (new User(2,"Anju","Anju@carrentals.com","Admin123","9876543210","License12/A/1","false",new Date(),new Date()));

            Vehicle vehicle1 = (new Vehicle(1,"Innova","Toyota","KL-10-H-21","12112840","12112840","Diesal",35000,120,new Date(),"false",1));
            Vehicle vehicle2 = (new Vehicle(2,"Ethios","Toyota","KL-10-H-22","12112840","12112840","Diesal",35000,120,new Date(),"false",2));

            trips.add(new Trips(1,user1,agency1,vehicle1,1,3,new Date(),new Date(),2,"Good",new Date(),true));
            trips.add(new Trips(2,user2,agency2,vehicle2,2,1,new Date(),new Date(),2,"Good",new Date(),true));
            trips.add(new Trips(3,user1,agency1,vehicle1,3,2,new Date(),new Date(),2,"Good",new Date(),true));
            trips.add(new Trips(4,user2,agency2,vehicle2,2,3,new Date(),new Date(),2,"Good",new Date(),true));
            trips.add(new Trips(5,user1,agency1,vehicle1,1,3,new Date(),new Date(),2,"Good",new Date(),true));

            trips.forEach(temp -> tripsServices.addNewTrip(temp));//lambda

            logger.info("Added 5 Offices using @PostConstruct annotation!!!");

        }
        catch(Exception exception)
        {

        }
    }

}
