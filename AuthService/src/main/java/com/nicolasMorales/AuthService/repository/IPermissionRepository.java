package com.nicolasMorales.AuthService.repository;


import com.nicolasMorales.AuthService.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long> {
}


