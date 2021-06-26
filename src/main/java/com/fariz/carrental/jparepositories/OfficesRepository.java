package com.fariz.carrental.jparepositories;

import com.fariz.carrental.dao.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfficesRepository extends JpaRepository<Office,Integer> {

    public List<Office> findAllByEmailId(String email);
    public Office findByEmailId(String email);

}
