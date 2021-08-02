package com.slcinema.services;

import com.slcinema.models.Admin;
import com.slcinema.models.AdminDetailsPrinciple;
import com.slcinema.repo.AdminRepo;
import com.slcinema.models.User;
import com.slcinema.models.UserDetailsPrinciple;
import com.slcinema.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("custom")
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Admin admin = adminRepo.findByUsername(username);
		User user = userRepo.findByUsername(username);

		if(admin != null) {
            return new AdminDetailsPrinciple(admin);
		}else if(user != null) {
            return new UserDetailsPrinciple(user);
		}
		throw new UsernameNotFoundException("User not found with username: " + username);
	}
}
