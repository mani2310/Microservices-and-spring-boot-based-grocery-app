package com.user.management.service;

import com.user.management.model.User;


public interface UserService {

	public User save(User user);

	//UserEntity findByUsername(String username);
	public String login(String username, String password);
	
    public boolean logout(String token);

    public Boolean isValidToken(String token);

    public String createNewToken(String token);


}
