package services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;


@Service
public class JwtService {


    private static final String SECRET_kEY = "DGYCGFDCYGCCYDGDYGDFDCGDCDCDTGD7D";

    public String getToken(UserDetails user) {
         return getToken(new HashMap<>(), user);
    }

    private  String getToken(HashMap<String, Object> extraClains, UserDetails user) {

        return Jwts
                .builder()
                .setClaims(extraClains)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey(), SignatureAlgorithm.ES256)
                .compact();
    }

    private Key getKey(){

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_kEY);
       return Keys.hmacShaKeyFor(keyBytes);
    }
}
