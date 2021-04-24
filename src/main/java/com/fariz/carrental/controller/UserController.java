package com.fariz.carrental.controller;

import com.fariz.carrental.services.*;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {


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
    @Autowired VehicleService vehicleService;


}
