package com.nicolasMorales.AuthService.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("denyAll()")
public class AuthController {

    @GetMapping("/holaseg")
    @PreAuthorize("hasAnyAuthority('READ')")
    public String holaTest () {
        return "hola, con seguridad";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/holanoseg")
    public String holaTest2 () {
        return "hola, sin seguridad";
    }
}
