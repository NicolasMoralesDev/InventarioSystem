package services;


import dtos.AuthResponse;
import dtos.LoginRequest;
import dtos.RegisterRequest;
import lombok.RequiredArgsConstructor;
import models.IUserRepository;
import models.Role;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService{

    @Autowired
    private IUserRepository userRepository;

    @Override
    public AuthResponse login(LoginRequest login) {
        return null;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstname());
        user.setLastName(request.getLastname());
        user.setRole(Role.USER);

        userRepository.save(user);
        AuthResponse hh = new AuthResponse();


        return  AuthResponse.builder()
                .token("a")
                .build();
    }
}
