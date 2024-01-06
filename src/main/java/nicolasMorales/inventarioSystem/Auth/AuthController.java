package nicolasMorales.inventarioSystem.Auth;


import nicolasMorales.inventarioSystem.dtos.AuthResponse;
import nicolasMorales.inventarioSystem.dtos.LoginRequest;
import nicolasMorales.inventarioSystem.dtos.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin (origins = "*")
public class AuthController {


      @Autowired
     private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return  ResponseEntity.ok(authService.login(request));
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){

       return  ResponseEntity.ok(authService.register(request));

    }
}
