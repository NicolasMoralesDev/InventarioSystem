package nicolasMorales.inventarioSystem.Auth;



import nicolasMorales.inventarioSystem.dtos.AuthResponse;
import nicolasMorales.inventarioSystem.dtos.LoginRequest;
import nicolasMorales.inventarioSystem.dtos.RegisterRequest;
import lombok.RequiredArgsConstructor;
import nicolasMorales.inventarioSystem.models.IUserRepository;
import nicolasMorales.inventarioSystem.models.Role;
import nicolasMorales.inventarioSystem.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    @Autowired
    private IUserRepository userRepository;


    @Autowired
    private JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest login) {
        return null;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {

        User user =  User.builder()
                .dni(request.getDni())
                .username(request.getUsername())
                .password(request.getPassword())
                .firstname(request.getFirstname())
                .lastName(request.getLastname())
                .role(Role.USER).build();

        userRepository.save(user);


        return  AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
