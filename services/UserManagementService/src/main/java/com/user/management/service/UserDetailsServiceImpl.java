package com.user.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.user.management.exception.CustomException;
import com.user.management.model.MongoUserDetails;
import com.user.management.model.Role;
import com.user.management.model.User;
import com.user.management.repository.UserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) 
	throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
        if (user == null || user.getRole() == null || user.getRole().isEmpty()) {
            throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
        }
        String [] authorities = new String[user.getRole().size()];
        int count=0;
        for (Role role : user.getRole()) {
            //NOTE: normally we dont need to add "ROLE_" prefix. Spring does automatically for us.
            //Since we are using custom token using JWT we should add ROLE_ prefix
            authorities[count] = "ROLE_"+role.getRole();
            count++;
        }
        MongoUserDetails userDetails = new MongoUserDetails(user.getEmail(),user.getPassword(),user.getActive(),
                user.isLoacked(), user.isExpired(),user.isEnabled(),authorities);
        return userDetails;
    }
	
	

}
