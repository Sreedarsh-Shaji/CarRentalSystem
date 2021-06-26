package com.fariz.carrental.controller;

import com.fariz.carrental.messages.Message;
import com.fariz.carrental.dao.Agency;
import com.fariz.carrental.dao.Office;
import com.fariz.carrental.dao.Trips;
import com.fariz.carrental.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping(value = "api/v1/Offices")
public class OfficesController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    AdminService adminService;
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

    //Login to the system and set session variable
    @PostMapping("/login")
    public Message officeLogin(@RequestBody Office office, HttpSession session)
    {
        if(officesService.loginOffice(office))
        {
            session.setAttribute("ActiveOffice",officesService.getOffice(office.getEmailId()));
        }
        else
        {
            session.setAttribute("ActiveOffice",null);
        }
        return Message.getMessage();
    }

    //Reset password
    @PostMapping("/updatePassword/{password}")
    public Message updatePassword(@PathVariable String password, HttpSession session)
    {
        Office office= (Office) session.getAttribute("ActiveOffice");

        if(officesService.loginOffice(office))
        {
            officesService.officeResetPassword(office,password);
            session.setAttribute("ActiveAgency",office);
        }
        else
        {
            session.setAttribute("ActiveAgency",null);
        }
        return Message.getMessage();
    }

    //Gets the list of all trips
    @RequestMapping(method = RequestMethod.GET , value = "/viewAllTrips")
    public Map<String,Object> getAllTrips(HttpSession session)
    {
        Map<String,Object> tripDetails = new HashMap<String, Object>();
        List<Trips> trips = adminService.adminSeeTripsList();
        Agency agency = (Agency)session.getAttribute("ActiveAgency");

        for (Trips temp:trips)
        {
            Map<String,Object> trip_data =  new HashMap<String, Object>();
            if(temp.getAgency().getEmail().equals(agency.getEmail())) {
                trip_data.put("trip_details", temp);
                trip_data.put("start_location", officesService.getOffice(temp.getPickupOfficeLocation()));
                trip_data.put("end_location", officesService.getOffice(temp.getPickupOfficeLocation()));
            }
            tripDetails.put(temp.getTripId()+"",trip_data);
        }
        return tripDetails;
    }

    //Creates set of offices
    @PostConstruct
    protected void createDummyAgencies()
    {
        try {

            List<Office> offices = new ArrayList<Office>();

            Agency agency1 = new Agency(7,"Anju","Anju@carrentals.com","Anju123","9876543210",new Date(),new Date(),true);
            Agency agency2 = new Agency(8,"Sumi","Sumi@carrentals.com","Sumi123","9876543210",new Date(),new Date(),true);

            offices.add(new Office(1,agency1,"Kollam","9876543210","9876543210","mail1@gmail.com","abc@123","8.9125415,76.6297183",new Date()));
            offices.add(new Office(2,agency2,"Trivandrum","9876543210","9876543210","mail2@gmail.com","abc@123","8.9125415,76.6297183",new Date()));
            offices.add(new Office(3,agency1,"Aalapuzha","9876543210","9876543210","mail3@gmail.com","8.9125415,76.6297183","abc@123",new Date()));
            offices.add(new Office(4,agency2,"pathanamthitta","9876543210","9876543210","mail4@gmail.com","8.9125415,76.6297183","abc@123",new Date()));
            offices.add(new Office(5,agency1,"Idukki","9876543210","9876543210","mail5@gmail.com","8.9125415,76.6297183","abc@123",new Date()));

            offices.forEach(temp -> officesService.addNewOffice(temp));//lambda

            logger.info("Added 5 Offices using @PostConstruct annotation!!!");

        }
        catch(Exception exception)
        {

        }
    }

}
