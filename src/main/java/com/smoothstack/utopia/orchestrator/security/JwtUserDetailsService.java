package com.smoothstack.utopia.orchestrator.security;

import com.smoothstack.utopia.orchestrator.dao.UserDao;
import com.smoothstack.utopia.shared.model.User;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Rob Maes
 * Mar 19 2021
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

  private final UserDao userDao;

  @Autowired
  public JwtUserDetailsService(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    User user = userDao
      .findUserByUsername(username)
      .orElseThrow(
        () ->
          new UsernameNotFoundException(
            "User not found with username: " + username
          )
      );
    ArrayList<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));
    return new org.springframework.security.core.userdetails.User(
      user.getUsername(),
      user.getPassword(),
      authorities
    );
  }
}
