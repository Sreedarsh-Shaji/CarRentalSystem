package com.fariz.carrental.services;

import com.fariz.carrental.dto.AgencyRepository;
import com.fariz.carrental.exceptionhadling.NoSuchAgency;
import com.fariz.carrental.messages.Message;
import com.fariz.carrental.messages.MessageType;
import com.fariz.carrental.model.Admin;
import com.fariz.carrental.dto.AdminRepository;
import com.fariz.carrental.model.Agency;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    public List<Admin> getAllAdmins()
    {
        return repo.findAll();
    }
    public void save(Admin admin){ repo.save(admin); }

    public List<Agency> adminSeeAgencyList()
    {
        return agencyRepository.findAll();
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
