package com.nicolasMorales.InventariumSystem.services.impl;

import com.nicolasMorales.InventariumSystem.dto.AuthLoginRequestDTO;
import com.nicolasMorales.InventariumSystem.dto.AuthResponseDTO;
import com.nicolasMorales.InventariumSystem.entity.UserSec;
import com.nicolasMorales.InventariumSystem.repository.IUserRepository;
import com.nicolasMorales.InventariumSystem.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            UserSec userSec = (UserSec) userRepository.findUserEntityByUsername(username).orElseThrow(()-> new UsernameNotFoundException("El usuario: "+ username + " no fue encontrado"));
            List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

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

    public AuthResponseDTO loginUser(AuthLoginRequestDTO userRequest) {
        String username = userRequest.username();
        String password = userRequest.password();

        Authentication authentication = this.authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO(username, "Longin correcto!", accessToken, true);

        return authResponseDTO;
    }

    public Authentication authenticate (String username, String password) {
        //con esto debo buscar el usuario
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }
        // si no es igual
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

}