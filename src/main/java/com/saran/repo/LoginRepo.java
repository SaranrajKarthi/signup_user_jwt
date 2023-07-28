package com.saran.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saran.entity.Login;

public interface LoginRepo extends JpaRepository<Login, Integer> {

}
