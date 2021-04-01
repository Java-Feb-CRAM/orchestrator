/**
 *
 */
package com.smoothstack.utopia.orchestrator.dao;

import com.smoothstack.utopia.shared.model.UserRole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
  public Optional<UserRole> findById(int id);

  public Optional<UserRole> findByName(String name);
}
