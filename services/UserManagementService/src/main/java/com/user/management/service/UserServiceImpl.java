package com.user.management.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.user.management.exception.CustomException;
import com.user.management.model.JwtToken;
import com.user.management.model.Role;
import com.user.management.model.User;
import com.user.management.repository.JwtTokenRepository;
import com.user.management.repository.UserRepository;
import com.user.management.security.JWTAuthenticationToken;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTAuthenticationToken jwtAuthToken;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JwtTokenRepository jwtTokenRepository;

	
	
    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()) );
        return userRepo.save(user);
    }

    @Override
    public boolean logout(String token) {
         jwtTokenRepository.delete(new JwtToken(token));
         return true;
    }

    @Override
    public Boolean isValidToken(String token) {
        return jwtAuthToken.validateToken(token);
    }

    @Override
    public String createNewToken(String token) {
        String username = jwtAuthToken.getUsername(token);
        List<String>roleList = jwtAuthToken.getRoleList(token);
        String newToken =  jwtAuthToken.createToken(username,roleList);
        return newToken;
    }
    
    @Override
    public String login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                    password));
            User user = userRepo.findByEmail(username);
            if (user == null || user.getRole() == null || user.getRole().isEmpty()) {
                throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
            }
            //NOTE: normally we dont need to add "ROLE_" prefix. Spring does automatically for us.
            //Since we are using custom token using JWT we should add ROLE_ prefix
            String token =  jwtAuthToken.createToken(username, user.getRole().stream()
                    .map((Role role)-> "ROLE_"+role.getRole()).filter(Objects::nonNull).collect(Collectors.toList()));
            return token;

        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
        }
    }


}
