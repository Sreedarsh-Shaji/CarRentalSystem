package com.fariz.carrental.services;

import com.fariz.carrental.jparepositories.*;
import com.fariz.carrental.exceptionhandling.NoSuchAgency;
import com.fariz.carrental.messages.Message;
import com.fariz.carrental.messages.MessageType;
import com.fariz.carrental.dao.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class AdminService {

    @Autowired
    private AdminRepository repo;

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private OfficesRepository officesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private TripsRepository tripsRepository;

    public List<Admin> getAllAdmins()
    {
        return repo.findAll();
    }
    public void save(Admin admin){ repo.save(admin); }

    public List<Agency> adminSeeAgencyList()
    {
        return agencyRepository.findAll();
    }

    public List<Office> adminSeeOfficesList()
    {
        return officesRepository.findAll();
    }

    public List<Trips> adminSeeTripsList()
    {
        return tripsRepository.findAll();
    }

    public List<User> adminSeeUserList()
    {
        return userRepository.findAll();
    }

    public List<Vehicle> adminSeeVehicleList()
    {
        return vehicleRepository.findAll();
    }

    public void approveAgency(int id)
    {
        if(agencyRepository.findById(id)==null){
            throw new NoSuchAgency();
        }
        else
        {
            Agency tempAgency = agencyRepository.getOne(id);
            if (!tempAgency.isApproved()) {
                tempAgency.setApproved(true);
                agencyRepository.save(tempAgency);
                Message.setMessage(MessageType.SUCCESS, "Approved Agency!", new Date());
            } else {
                Message.setMessage(MessageType.FAILURE, "Already Approved Agency!", new Date());
            }
        }
    }

}
