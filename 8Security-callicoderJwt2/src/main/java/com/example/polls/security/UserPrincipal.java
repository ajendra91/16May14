package com.example.polls.security;

import com.example.polls.config.User;

import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@NoArgsConstructor
public class UserPrincipal implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public User emp;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_"+emp.role));
    }

    @Override
    public String getPassword() {
        return emp.password;
    }

    @Override
    public String getUsername() {
        return emp.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
