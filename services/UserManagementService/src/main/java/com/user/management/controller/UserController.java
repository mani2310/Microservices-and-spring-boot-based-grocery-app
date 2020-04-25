package com.user.management.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.management.model.LoginRequest;
import com.user.management.service.UserService;
import com.user.management.model.User;
import com.user.management.repository.UserRepository;
import com.user.management.model.Role;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	UserRepository userRepository;
	@CrossOrigin("*")
    @PostMapping("/createUser")
    @ResponseBody
    public void createUser(@RequestBody User user) {
        //User user1 = new User();
        List<Role> list = Arrays.asList(new Role(1,"ADMIN"),new Role(2,"USER"));
        Set<Role> s=new HashSet<>(list);
        user.setRole(s);
        //userRepository.save(user);
        userService.save(user);
    }

	@PostMapping("/signin")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		String token = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
		HttpHeaders headers = new HttpHeaders();
		List<String> headerlist = new ArrayList<>();
		List<String> exposeList = new ArrayList<>();
		headerlist.add("Content-Type");
		headerlist.add(" Accept");
		headerlist.add("X-Requested-With");
		headerlist.add("Authorization");
		headers.setAccessControlAllowHeaders(headerlist);
		exposeList.add("Authorization");
		headers.setAccessControlExposeHeaders(exposeList);
 		headers.set("Authorization", token);
		return new ResponseEntity<>(token, headers, HttpStatus.CREATED);
	}
	
	  /**
    *
    * @param token
    * @return boolean.
    * if request reach here it means it is a valid token.
    */
   @PostMapping("/valid/token")
   @ResponseBody
   public Boolean isValidToken (@RequestHeader(value="Authorization") String token) {
       return true;
   }


   @PostMapping("/signin/token")
   @CrossOrigin("*")
   @ResponseBody
   public ResponseEntity<?> createNewToken (@RequestHeader(value="Authorization") String token) {
       String newToken = userService.createNewToken(token);
       HttpHeaders headers = new HttpHeaders();
       List<String> headerList = new ArrayList<>();
       List<String> exposeList = new ArrayList<>();
       headerList.add("Content-Type");
       headerList.add(" Accept");
       headerList.add("X-Requested-With");
       headerList.add("Authorization");
       headers.setAccessControlAllowHeaders(headerList);
       exposeList.add("Authorization");
       headers.setAccessControlExposeHeaders(exposeList);
       headers.set("Authorization", newToken);
       return new ResponseEntity<>(newToken, headers, HttpStatus.CREATED);
   }

}
