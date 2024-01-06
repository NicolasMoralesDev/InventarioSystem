package nicolasMorales.inventarioSystem.dtos;


import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String token;

}
