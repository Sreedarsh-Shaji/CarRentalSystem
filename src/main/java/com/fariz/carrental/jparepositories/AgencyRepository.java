package com.fariz.carrental.jparepositories;

import com.fariz.carrental.dao.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgencyRepository extends JpaRepository<Agency,Integer> {

    public List<Agency> findAllByEmail(String email);
    public Agency findByEmail(String email);

}
