package com.serviceuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serviceuser.domain.Role;



public interface RoleRepo extends JpaRepository<Role, Long> {
	
	 Role findByName(String name);

}
