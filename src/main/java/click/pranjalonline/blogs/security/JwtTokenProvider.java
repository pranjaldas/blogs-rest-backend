package click.pranjalonline.blogs.security;

import click.pranjalonline.blogs.exceptions.BusinessLogicException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String SECRET_KEY;
    @Value("${app.jwt-expiration-milliseconds}")
    private int expirationTime;
    /**
     * To generate the token
     * */
    public String generateToken(Authentication authentication){
        String username= authentication.getName();
        Date currentTime= new Date();
        Date expiryDate= new Date(currentTime.getTime()+expirationTime);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
    }
    /**
     * To get username from JWT token
     * */
    public  String getUsernameFromJWT(String token){
        Claims claims= Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
    /**
     *  Validate JWT token
     */
    public boolean validateJWTToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return  true;
        }catch (SignatureException e){
            throw new BusinessLogicException(HttpStatus.BAD_REQUEST,"Invalid JWT Signature");
        }catch (MalformedJwtException e){
            throw new BusinessLogicException(HttpStatus.BAD_REQUEST,"Invalid JWT token");
        }catch (ExpiredJwtException e){
            throw new BusinessLogicException(HttpStatus.BAD_REQUEST,"JWT token expired");
        }catch (UnsupportedJwtException e){
            e.printStackTrace();
            throw new BusinessLogicException(HttpStatus.BAD_REQUEST,"Unsupported JWT token");
        }catch (IllegalArgumentException e){
            throw new BusinessLogicException(HttpStatus.BAD_REQUEST,"Invalid JWT Signature");
        }
    }
}


