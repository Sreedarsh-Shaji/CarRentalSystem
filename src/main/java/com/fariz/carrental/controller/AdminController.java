package com.fariz.carrental.controller;

import com.fariz.carrental.messages.Message;
import com.fariz.carrental.model.Admin;
import com.fariz.carrental.model.Agency;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fariz.carrental.services.AdminService;

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
    List<AdminController> dummyAdmins = new ArrayList<AdminController>();

    @RequestMapping(method = RequestMethod.GET,value = "/addDummyAdmins")
    public String addAll()
    {
        Admin a=new Admin(2,"Super Admin","admin@carrentals.com","Admin123",new Date(),new Date());
        service.save(a);
        logger.info("Dummy admins added!!!");
        return ("Dummy admins added!!!");
    }

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
