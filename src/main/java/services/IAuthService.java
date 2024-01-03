package services;

import dtos.AuthResponse;
import dtos.LoginRequest;
import dtos.RegisterRequest;

public interface IAuthService {

    public AuthResponse login (LoginRequest login);
    public AuthResponse register (RegisterRequest request);
}
