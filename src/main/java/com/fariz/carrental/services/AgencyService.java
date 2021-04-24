package com.fariz.carrental.services;

import com.fariz.carrental.dto.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyService {

    @Autowired
    AgencyRepository repository;

}
