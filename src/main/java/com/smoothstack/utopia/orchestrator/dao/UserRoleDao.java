package com.smoothstack.utopia.orchestrator.dao;

import com.smoothstack.utopia.shared.model.UserRole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rob Maes
 * Mar 19 2021
 */
@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Long> {
  Optional<UserRole> findUserRoleByName(String name);
}
