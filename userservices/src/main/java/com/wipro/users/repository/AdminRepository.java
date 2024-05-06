package com.wipro.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.users.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
