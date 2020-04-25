package com.user.management.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailService {

	UserDetails loadUserByName(String userName);

}
