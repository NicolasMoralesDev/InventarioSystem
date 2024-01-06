package nicolasMorales.inventarioSystem.Config;

import lombok.RequiredArgsConstructor;
import nicolasMorales.inventarioSystem.models.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {


    private IUserRepository userRepository;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws  Exception{

        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(AuthenticationConfiguration config) throws  Exception{

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();

    }
    @Bean
    public UserDetailsService userDetailsService(){

        return username -> (UserDetails) userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

}



