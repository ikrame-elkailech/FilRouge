package com.example.filrouge.Security;

import com.example.filrouge.Dtos.UserDto;
import com.example.filrouge.Service.IUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class UserDetailsServiceImpl implements UserDetailsService {
    private final IUserService iUserService;

    public UserDetailsServiceImpl(IUserService iUserService) {

        this.iUserService = iUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        UserDto user = iUserService.getByEmailInObject(email);
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole().name());
        return new User(user.getEmail(), user.getPassword(), Collections.singleton(authority));
    }
}
