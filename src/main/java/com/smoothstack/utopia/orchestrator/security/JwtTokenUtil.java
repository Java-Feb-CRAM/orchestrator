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
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Craig Saunders
 *
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenUtil {
    
    @Value("jwt.secret")
    private final String jwtSecret;

    public String getTokenUsername(String token) 
    {
        return getTokenClaims(token).getSubject();
    }
    
    public Date getTokenExpirationDate(String token) {
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