package com.nicolasMorales.InventariumSystem.controllers.auth;

import com.nicolasMorales.InventariumSystem.dto.AuthLoginRequestDTO;
import com.nicolasMorales.InventariumSystem.dto.AuthResponseDTO;
import com.nicolasMorales.InventariumSystem.services.impl.UserDetailsServiceImpl;
import com.nicolasMorales.InventariumSystem.utils.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    //Todas estas requests y responses vamos a tratarlas como dto
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthLoginRequestDTO userRequest) {
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }

}