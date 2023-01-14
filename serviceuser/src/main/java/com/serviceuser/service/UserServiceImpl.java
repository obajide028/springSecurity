package com.serviceuser.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serviceuser.domain.AppUser;
import com.serviceuser.domain.Role;
import com.serviceuser.repository.RoleRepo;
import com.serviceuser.repository.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 		AppUser user = userRepo.findByUsername(username);
 		if(user == null) {
 			throw new UsernameNotFoundException("User not found in the database "+ username);
 		}else {
 			System.out.print("User found");
 		}
 		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
 		user.getRoles().forEach(role ->{
 			authorities.add(new SimpleGrantedAuthority(role.getName()));
 		});
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

	}

	@Override
	public AppUser saveUser(AppUser user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		
		AppUser user = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		
		user.getRoles().add(role);
		
	}

	@Override
	public AppUser getUser(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public List<AppUser> getUser() {
		return userRepo.findAll();
	}



}
