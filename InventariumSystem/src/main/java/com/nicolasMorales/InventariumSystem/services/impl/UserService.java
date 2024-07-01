package com.nicolasMorales.InventariumSystem.services.impl;

import com.nicolasMorales.InventariumSystem.entity.UserSec;
import com.nicolasMorales.InventariumSystem.mapper.UserMapper;
import com.nicolasMorales.InventariumSystem.repository.IUserRepository;
import com.nicolasMorales.InventariumSystem.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List findAll() {

        List usuarios = userRepository.findAll().stream().map(usuario -> userMapper.userSecaUserDTO(usuario)).toList();
        return usuarios;
    }

    @Override
    public Optional findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserSec save(UserSec userSec) {
        return userRepository.save(userSec);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(UserSec userSec) {
        save(userSec);
    }

    @Override
    public String encriptPasword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}