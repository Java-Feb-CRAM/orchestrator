/**
 * 
 */
package com.smoothstack.utopia.orchestrator.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smoothstack.utopia.shared.model.User;
import com.smoothstack.utopia.orchestrator.dao.UserRepository;

/**
 * @author Craig Saunders
 *
 */
@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No user with username: " + username));    
        return new UserPrincipal(user.getUsername(), user.getPassword(), user.getUserRole().getName());
    }
}