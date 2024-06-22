package com.nicolasMorales.AuthService.repository;


import com.nicolasMorales.AuthService.model.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserSec, Long> {

    Optional findUserEntityByUsername(String username);

}

