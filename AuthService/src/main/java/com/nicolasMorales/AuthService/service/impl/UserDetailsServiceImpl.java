package com.nicolasMorales.AuthService.service.impl;


import com.nicolasMorales.AuthService.model.UserSec;
import com.nicolasMorales.AuthService.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            UserSec userSec = (UserSec) userRepository.findUserEntityByUsername(username).orElseThrow(()-> new UsernameNotFoundException("El usuario: "+ username + " no fue encontrado"));
            List <SimpleGrantedAuthority> authorityList = new ArrayList<>();

            userSec.getRolesList()
                            .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole()))));

            userSec.getRolesList().stream()
                    .flatMap(role -> role.getPermissionsList().stream())
                    .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));

            return  new User(
                    userSec.getUsername(),
                    userSec.getPassword(),
                    userSec.isEnabled(),
                    userSec.isAccountNotExpired(),
                    userSec.isCredentialNotExpired(),
                    userSec.isAccountNotLocked(),
                    authorityList);

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
