package com.serviceuser.service;

import java.util.List;

import com.serviceuser.domain.AppUser;
import com.serviceuser.domain.Role;

public interface UserService {
	
	AppUser saveUser(AppUser user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String roleName);
	AppUser getUser(String username);
	List<AppUser> getUser();

}
