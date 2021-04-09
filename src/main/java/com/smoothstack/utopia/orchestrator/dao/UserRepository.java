/**
 *
 */
package com.smoothstack.utopia.orchestrator.dao;

import com.smoothstack.utopia.shared.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  public Optional<User> findByUsername(String username);

  public Optional<User> findByEmail(String email);
}
