package com.example.polls.controller;

import com.example.polls.config.User;
import com.example.polls.security.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
public class AuthController {

    public List<User> lst =new ArrayList<User>();
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtTokenProvider tokenProvider;


    @GetMapping("/signin")
    public ResponseEntity<?> authenticateUser() {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        "admin",
                        "admin"
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
    
  
    @PostConstruct
    public void add() {
    	User user1=new User(1,"user","user","USER");
    	lst.add(user1);
    	
    	User user2=new User(2,"admin","admin","ADMIN");
    	lst.add(user2);
    	
    }


    public User findByName(String name){
        User newEmp=null;
        for (int i=0;i<lst.size();i++){
            User e=lst.get(i);
            if(e.username.equals(name)){
                newEmp=e;
            }
        }
        return newEmp;
    }


	public User findById(Long id) {
		 User newEmp=null;
	        for (int i=0;i<lst.size();i++){
	            User e=lst.get(i);
	            if(e.id==id){
	                newEmp=e;
	            }
	        }
	        return newEmp;
	}
  
}
