/**
 * 
 */
/**
 * 
 */
package com.smoothstack.utopia.orchestrator.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.smoothstack.utopia.orchestrator.dao.UserRepository;

import java.util.Date;

import static java.lang.String.format;

/**
 * @author Craig Saunders
 *
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtil {
    @Autowired
    public UserRepository userRepository;

    /* BEGIN REFFERENCE FOR GENERATING THE CORRECT ACCESS TOKEN */
    @Value("${UT_JWT_SECRET}")
    private String jwtSecret;
    
    private final String jwtIssuer = "utopia.smoothstack.com";
    private final int ONE_WEEK_MILLISECONDS = 7 * 24 * 60 * 60 * 1000;
    
    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(format("%s", username))
                .setIssuedAt(new Date())
                .setIssuer(jwtIssuer)
                .setExpiration(new Date(System.currentTimeMillis() + ONE_WEEK_MILLISECONDS)) // 1 week
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    /* END REFFERENCE FOR GENERATING THE CORRECT ACCESS TOKEN */
    
    public String getTokenUsername(String token) 
    {
        return getTokenClaims(token).getSubject();
    }
    
    public Date getTokenExpirationDate(String token) 
    {
        return getTokenClaims(token).getExpiration();
    }

    public Claims getTokenClaims(String token)
    {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }
    
    public boolean validate(String token) {        
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

}