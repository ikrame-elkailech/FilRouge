package com.example.filrouge.Configuration;

import com.example.filrouge.Dtos.UserDto;
import com.example.filrouge.Service.IUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private IUserService utilisateurService;

    public UserDetailsServiceImpl(IUserService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto utilisateur = utilisateurService.getByEmailInObject(email);
        if (utilisateur == null) throw new UsernameNotFoundException("USER NOT FOUND");
        GrantedAuthority authority = new SimpleGrantedAuthority(utilisateur.getUserRole().name());
        return new User(utilisateur.getEmail(),utilisateur.getPassword(), Collections.singleton(authority));

    }

}

