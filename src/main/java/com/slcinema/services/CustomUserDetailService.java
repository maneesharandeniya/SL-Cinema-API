package com.slcinema.services;

import com.slcinema.models.Admin;
import com.slcinema.models.AdminDetailsPrincipal;
import com.slcinema.repo.AdminRepo;
import com.slcinema.models.User;
import com.slcinema.models.UserDetailsPrincipal;
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
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Admin admin = adminRepo.findByEmail(email);
		User user = userRepo.findByEmail(email);

		if(admin != null) {
            return new AdminDetailsPrincipal(admin);
		}else if(user != null) {
            return new UserDetailsPrincipal(user);
		}
		throw new UsernameNotFoundException("User not found with email: " + email);
	}


}
