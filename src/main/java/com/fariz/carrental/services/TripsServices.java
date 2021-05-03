package com.fariz.carrental.services;

import com.fariz.carrental.dto.AgencyRepository;
import com.fariz.carrental.dto.TripsRepository;
import com.fariz.carrental.model.Trips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripsServices {

    @Autowired
    TripsRepository repository;

    public void addNewTrip(Trips trip)
    {
        repository.save(trip);
    }

}
