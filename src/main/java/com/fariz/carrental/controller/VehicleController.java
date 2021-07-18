package com.fariz.carrental.controller;

import com.fariz.carrental.dao.Agency;
import com.fariz.carrental.dao.Office;
import com.fariz.carrental.dao.Vehicle;
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
@RequestMapping(value = "api/v1/vehicle")
public class VehicleController {

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

            List<Vehicle> vehicles = new ArrayList<Vehicle>();

            Agency agency1 = (new Agency(7,"Sumi","Sumi@carrentals.com","Sumi123","9876543210",new Date(),new Date(),true));
            Agency agency2 = (new Agency(8,"Sudheesh","sudheesh@carrentals.com","Sudheesh123","9876543210",new Date(),new Date(),true));

            Office office1 = new Office(1,agency1,"Kollam","9876543210","9876543210","mail@gmail.com","asd123","8.9125415,76.6297183",new Date());
            Office office2 = new Office(2,agency2,"Trivandrum","9876543210","9876543210","mail@gmail.com","asd123","8.9125415,76.6297183",new Date());

            vehicles.add(new Vehicle(1,"Innova","Toyota","KL-10-H-21","12112840","12112840","Diesal",35000,120,new Date(),"false",1));
            vehicles.add(new Vehicle(2,"Ethios","Toyota","KL-10-H-22","12112840","12112840","Diesal",35000,120,new Date(),"false",1));
            vehicles.add(new Vehicle(3,"Figo Aspire","Ford","KL-10-H-23","12112840","12112840","Diesal",35000,120,new Date(),"false",1));
            vehicles.add(new Vehicle(4,"Xcent","hyundai","KL-10-H-24","12112840","12112840","Diesal",35000,120,new Date(),"false",1));
            vehicles.add(new Vehicle(5,"Enjoy","chevrolet ","KL-10-H-25","12112840","12112840","Diesal",35000,120,new Date(),"false",1));
            vehicles.add(new Vehicle(6,"Dezire","Suzuki","KL-10-H-26","12112840","12112840","Diesal",35000,120,new Date(),"false",1));

            vehicles.forEach(temp -> vehicleService.addNewVehicle(temp));//lambda

            logger.info("Added 5 vehicles using @PostConstruct annotation!!!");

        }
        catch(Exception exception)
        {

        }
    }

}
