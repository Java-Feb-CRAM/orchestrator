package com.smoothstack.utopia.orchestrator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author Rob Maes
 * Mar 19 2021
 */
@Component
public class JwtTokenUtil implements Serializable {

  @Serial
  private static final long serialVersionUID = -2657027816134692423L;

  @Value("${jwt.secret}")
  private String secret;

  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public <T> T getClaimFromToken(
    String token,
    Function<Claims, T> claimsResolver
  ) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    return (
      username.equals(userDetails.getUsername()) && !isTokenExpired(token)
    );
  }
}
