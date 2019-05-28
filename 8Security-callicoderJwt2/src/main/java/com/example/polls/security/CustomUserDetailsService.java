package com.example.polls.security;

import com.example.polls.config.User;
import com.example.polls.controller.AuthController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


	@Autowired
	public AuthController auth;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
    	User emp =auth.findByName(usernameOrEmail);
    	UserPrincipal empCustom=null;
        if(emp!=null){
            empCustom=new UserPrincipal();
            empCustom.emp=emp;
        }else{
            throw new UsernameNotFoundException("emp not found");
        }
        return empCustom;
      
    }

    public UserDetails loadUserById(Long id) {
    	User emp =auth.findById(id);
    	UserPrincipal empCustom=null;
        if(emp!=null){
            empCustom=new UserPrincipal();
            empCustom.emp=emp;
        }else{
            throw new UsernameNotFoundException("emp not found");
        }
        return empCustom;
    }
}