package com.fariz.carrental.services;

import com.fariz.carrental.dao.Admin;
import com.fariz.carrental.jparepositories.AgencyRepository;
import com.fariz.carrental.jparepositories.OfficesRepository;
import com.fariz.carrental.messages.Message;
import com.fariz.carrental.messages.MessageType;
import com.fariz.carrental.dao.Agency;
import com.fariz.carrental.dao.Office;
import com.fariz.carrental.utils.Time;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AgencyService {

    @Autowired
    AgencyRepository repository;
    @Autowired
    OfficesRepository officesRepository;



    public void signUpAgency(Agency temp)
    {
        repository.save(temp);
    }

    public Optional<Agency> getAgency(int id)
    {
        return repository.findById(id);
    }



    public Agency getAgency(String email)
    {
        return repository.findByEmail(email);
    }

    public boolean isDuplicateEmail(String mail)
    {
        if(repository.findAllByEmail(mail).size()>0)
        {
            return true;
        }
        return false;
    }

    public boolean loginAgency(@NotNull Agency testAgency)
    {
        List<Agency> allAgencies = repository.findAllByEmail(testAgency.getEmail());
        if(allAgencies.size()<=0)
        {
            Message.setMessage(MessageType.ERROR,"Invalid details!!!",new Date());
            return false;
        }
        else
        {
            if(testAgency.getPassword().trim().equals(allAgencies.get(0).getPassword().trim()))
            {
                testAgency.setLastLogin(Time.getTime());
                repository.save(testAgency);
                Message.setMessage(MessageType.SUCCESS,"Login Success!!!",new Date());
                return true;
            }
            else
            {
                Message.setMessage(MessageType.ERROR,"Invalid details!!!", Time.getTime());
                return false;
            }
        }

    }

    public Agency agencyResetPassword(@NotNull Agency testAgency,String password)
    {
        try {
            testAgency.setPassword(password);
            testAgency.setLastLogin(Time.getTime());
            repository.save(testAgency);
            Message.setMessage(MessageType.SUCCESS, "updated password!!!", Time.getTime());
            return testAgency;
        }
        catch(Exception ex){
            return null;
        }
    }

    public void addOffice(Office office)
    {
        officesRepository.save(office);
        Message.setMessage(MessageType.SUCCESS,"Added office successfully",new Date());
    }

    public void removeOffice(Office office)
    {
        officesRepository.delete(office);
        Message.setMessage(MessageType.SUCCESS,"Added office successfully",new Date());
    }

    public void deleteAgency(int id) {
        repository.deleteById(id);
    }

    public void agencySignUp(Agency agency) {
        repository.save(agency);
    }
    public List<Office> getOfficesByAgency()
    {
       return  officesRepository.findAll();
    }
}
