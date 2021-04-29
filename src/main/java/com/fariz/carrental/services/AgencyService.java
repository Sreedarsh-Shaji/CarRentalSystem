package com.fariz.carrental.services;

import com.fariz.carrental.dto.AgencyRepository;
import com.fariz.carrental.messages.Message;
import com.fariz.carrental.messages.MessageType;
import com.fariz.carrental.model.Agency;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AgencyService {

    @Autowired
    AgencyRepository repository;

    public void addNewAgency(Agency temp)
    {
        repository.save(temp);
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
                testAgency.setLastLogin(new Date());
                repository.save(testAgency);
                Message.setMessage(MessageType.SUCCESS,"Login Success!!!",new Date());
                return true;
            }
            else
            {
                Message.setMessage(MessageType.ERROR,"Invalid details!!!",new Date());
                return false;
            }
        }

    }

}
