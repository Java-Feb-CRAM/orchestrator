package com.smoothstack.utopia.orchestrator.dao;

import com.smoothstack.utopia.shared.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rob Maes
 * Mar 19 2021
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
  Optional<User> findUserByUsername(String username);
  Optional<User> findUserByEmail(String email);
  Optional<User> findUserByUsernameAndPassword(
    String username,
    String password
  );
}
