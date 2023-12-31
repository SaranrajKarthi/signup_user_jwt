package com.saran.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saran.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	User findByEmail(String email);

}
