package com.fariz.carrental.services;

import com.fariz.carrental.dto.OfficesRepository;
import com.fariz.carrental.messages.Message;
import com.fariz.carrental.messages.MessageType;
import com.fariz.carrental.model.Agency;
import com.fariz.carrental.model.Office;
import com.fariz.carrental.utils.Time;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OfficesService {

    @Autowired
    OfficesRepository repository;

    public void addNewOffice(Office temp)
    {
        repository.save(temp);
    }

    public Optional<Office> getOffice(int key){ return repository.findById(key); }


    public Office getOffice(String email)
    {
        return repository.findByEmailId(email);
    }

    public boolean isDuplicateEmail(String mail)
    {
        if(repository.findAllByEmailId(mail).size()>0)
        {
            return true;
        }
        return false;
    }

    public boolean loginOffice(@NotNull Office testAgency)
    {
        List<Office> allOffices = repository.findAllByEmailId(testAgency.getEmailId());
        if(allOffices.size()<=0)
        {
            Message.setMessage(MessageType.ERROR,"Invalid details!!!",new Date());
            return false;
        }
        else
        {
            if(testAgency.getPassword().trim().equals(allOffices.get(0).getPassword().trim()))
            {
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

    public Office officeResetPassword(@NotNull Office testOffice,String password)
    {
        try {
            testOffice.setPassword(password);
            repository.save(testOffice);
            Message.setMessage(MessageType.SUCCESS, "updated password!!!", Time.getTime());
            return testOffice;
        }
        catch(Exception ex){
            return null;
        }
    }

}
