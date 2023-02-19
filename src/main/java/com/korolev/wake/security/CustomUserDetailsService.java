package com.korolev.wake.security;

import com.korolev.wake.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.korolev.wake.model.User entry = usersRepository.getByLogin(username);
        if(entry != null) {
            return new User(entry.getLogin(), entry.getPassword(), new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("user with this username and password does not exists");
        }
    }
}
