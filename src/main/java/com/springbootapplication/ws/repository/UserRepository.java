package com.springbootapplication.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootapplication.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
