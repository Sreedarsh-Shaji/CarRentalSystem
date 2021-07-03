package com.fariz.carrental.controller;

import com.fariz.carrental.dao.Admin;
import com.fariz.carrental.messages.Message;
import com.fariz.carrental.messages.MessageType;
import com.fariz.carrental.dao.Agency;
import com.fariz.carrental.dao.Office;
import com.fariz.carrental.dao.Trips;
import com.fariz.carrental.services.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping(value = "api/v1/agency")
@Api("Agency APIs")
public class AgencyController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired AdminService adminService;
    @Autowired AgencyService agencyService;
    @Autowired OfficesService officesService;
    @Autowired TripsServices tripsServices;
    @Autowired UserService userService;
    @Autowired VehicleService vehicleService;

    //Signup to the system without verification
    @PostMapping("/signup")
    public Message agencySignup(@RequestBody Agency agency)
    {
        if (!agencyService.isDuplicateEmail(agency.getEmail()))
        {
            agencyService.addNewAgency(agency);
            Message.setMessage(MessageType.SUCCESS,"Created new agency",new Date());
        }
        else {
            Message.setMessage(MessageType.FAILURE,"Duplicate Email",new Date());
        }
        return Message.getMessage();
    }

    //Login to the system and set session variable
    @PostMapping("/login")
    public Message agencyLogin(@RequestBody Agency agency, HttpSession session)
    {
        if(agencyService.loginAgency(agency))
        {
            session.setAttribute("ActiveAgency",agencyService.getAgency(agency.getEmail()));
        }
        else
        {
            session.setAttribute("ActiveAgency",null);
        }
        return Message.getMessage();
    }

    //Reset password
    @PostMapping("/updatePassword/{password}")
    public Message updatePassword(@PathVariable String password, HttpSession session)
    {
        Agency agency = (Agency) session.getAttribute("ActiveAgency");

        if(agencyService.loginAgency(agency))
        {
            agencyService.agencyResetPassword(agency,password);
            session.setAttribute("ActiveAgency",agency);
        }
        else
        {
            session.setAttribute("ActiveAgency",null);
        }
        return Message.getMessage();
    }

    //add office under the agency
    @PostMapping("/addOffice")
    public Message addOffice(@RequestBody Office office, HttpSession session)
    {
        Agency agency = (Agency)session.getAttribute("ActiveAgency");
        if(agency!=null) {
            office.setAgency(agency);
            agencyService.addOffice(office);
        }
        else
        {
            Message.setMessage(MessageType.FAILURE,"Session timeout",new Date());
        }
        return Message.getMessage();
    }

    //remove office under the agency
    @DeleteMapping("/removeOffice/{id}")
    public Message removeOffice(@PathVariable int officeId, HttpSession session)
    {
        Agency agency = (Agency)session.getAttribute("ActiveAgency");
        Office office = officesService.getOffice(officeId).get();

        if(agency!=null)
        {
            agencyService.removeOffice(office);
        }
        else
        {
            Message.setMessage(MessageType.FAILURE,"Session timeout",new Date());
        }
        return Message.getMessage();
    }

    //Update office under the agency
    @PutMapping("/updateOffice")
    public Message updateOffice(@RequestBody Office office, HttpSession session)
    {
        Agency agency = (Agency)session.getAttribute("ActiveAgency");

        if(agency!=null)
        {
            agencyService.removeOffice(office);
            agencyService.addOffice(office);
            Message.setMessage(MessageType.SUCCESS,"Office updated successfully",new Date());
        }
        else
        {
            Message.setMessage(MessageType.FAILURE,"Session timeout",new Date());
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

    //Agency authentication
    @ApiOperation( value = "Agency authentication")
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET , value = "/agencyLogin/{username}/{password}")
    public Agency agencyLogIn(@PathVariable String username, @PathVariable String password)
    {
        List<Agency> agencies = adminService.adminSeeAgencyList();
        Agency retAgency = null;

        for (Agency temp : agencies)
        {
            if(temp.getEmail().equals(username)
                    && temp.getPassword().equals(password))
            {
                retAgency = temp;
            }
        }
        return retAgency;
    }

    //Creates set of dummy agencies
    @PostConstruct
    protected void createDummyAgencies()
    {
        try {
            List<Agency> agencies = new ArrayList<Agency>();

            agencies.add(new Agency(5,"Anju","Anju1@carrentals.com","Anju123","9876543210",new Date(),new Date(),true));
            agencies.add(new Agency(6,"Anju","Anju@carrentals.com","Anju123","9876543210",new Date(),new Date(),true));
            agencies.add(new Agency(7,"Sumi","Sumi@carrentals.com","Sumi123","9876543210",new Date(),new Date(),true));
            agencies.add(new Agency(8,"Sudheesh","sudheesh@carrentals.com","Sudheesh123","9876543210",new Date(),new Date(),true));
            agencies.add(new Agency(9,"Sreedarsh","sreedarsh@carrentals.com","sreedarsh123","9876543210",new Date(),new Date(),true));

            agencies.forEach(temp -> agencyService.addNewAgency(temp));//lambda

            logger.info("Added 5 admins using @PostConstruct annotation!!!");
        }
        catch(Exception exception)
        {

        }
    }


}
