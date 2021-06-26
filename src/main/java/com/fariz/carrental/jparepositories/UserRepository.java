package com.fariz.carrental.jparepositories;

import com.fariz.carrental.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
