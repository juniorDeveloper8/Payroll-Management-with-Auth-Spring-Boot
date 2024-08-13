package com.api.rober.Security;

import com.api.rober.Interface.LoginInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticationService implements UserDetailsService {

    @Autowired
    private LoginInterface loginInterface;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return loginInterface.findByUsername(username);
    }
}