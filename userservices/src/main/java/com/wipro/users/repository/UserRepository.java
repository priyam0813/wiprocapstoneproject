package com.wipro.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
