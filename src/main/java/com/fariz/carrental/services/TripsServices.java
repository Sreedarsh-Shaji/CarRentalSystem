package com.fariz.carrental.services;

import com.fariz.carrental.jparepositories.TripsRepository;
import com.fariz.carrental.dao.Trips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripsServices {

    @Autowired
    TripsRepository repository;

    public void addNewTrip(Trips trip)
    {
        repository.save(trip);
    }

    public void tripDelete(int id) {
        Trips trips = repository.findById(id).get();
        trips.setActive(false);
        repository.save(trips);
    }

    public List<Trips> seeAllTrips()
    {
        return repository.findAll();
    }
}
