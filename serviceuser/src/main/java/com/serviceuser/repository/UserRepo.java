package com.serviceuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serviceuser.domain.AppUser;



public interface UserRepo extends JpaRepository<AppUser, Long> {
	
	 AppUser findByUsername(String username);

}
