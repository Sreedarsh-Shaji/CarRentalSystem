package com.fariz.carrental.controller;

import com.fariz.carrental.messages.Message;
import com.fariz.carrental.messages.MessageType;
import com.fariz.carrental.model.Agency;
import com.fariz.carrental.services.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping(value = "api/v1/agency")
@Api("Agency APIs")
public class AgencyController {

    @Autowired AdminService adminService;
    @Autowired AgencyService agencyService;
    @Autowired OfficesService officesService;
    @Autowired TripsServices tripsServices;
    @Autowired UserService userService;
    @Autowired VehicleService vehicleService;

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

    @PostMapping("/login")
    public Message agencyLogin(@RequestBody Agency agency, HttpSession session)
    {
        if(agencyService.loginAgency(agency))
        {
            session.setAttribute("ActiveAgency",agency);
        }
        else
        {
            session.setAttribute("ActiveAgency",null);
        }
        return Message.getMessage();
    }

}
