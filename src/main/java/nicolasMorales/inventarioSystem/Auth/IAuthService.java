package nicolasMorales.inventarioSystem.Auth;

import nicolasMorales.inventarioSystem.dtos.AuthResponse;
import nicolasMorales.inventarioSystem.dtos.LoginRequest;
import nicolasMorales.inventarioSystem.dtos.RegisterRequest;

public interface IAuthService {

    public AuthResponse login (LoginRequest login);
    public AuthResponse register (RegisterRequest request);
}
