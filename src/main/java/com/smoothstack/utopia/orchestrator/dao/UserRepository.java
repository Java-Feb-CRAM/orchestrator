/**
 * 
 */
package com.smoothstack.utopia.orchestrator.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.utopia.shared.model.User;


/**
 * @author Craig Saunders
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {    
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
}
