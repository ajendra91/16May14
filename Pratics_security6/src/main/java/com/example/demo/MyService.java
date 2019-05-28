package com.example.demo;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 if (!username.equals("user")) {
	            throw new UsernameNotFoundException("not found");
	        }
		return new User("user","user",Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
	}
	
	
	

}
