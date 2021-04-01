package com.smoothstack.utopia.orchestrator.dto;

import com.smoothstack.utopia.shared.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Rob Maes
 * Apr 01 2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {

  private Long id;
  private String role;
  private String email;
  private String username;
  private String givenName;
  private String familyName;
  private String phoneNumber;

  public UserInfoDto(User user) {
    this.id = user.getId();
    this.role = user.getUserRole().getName();
    this.email = user.getEmail();
    this.username = user.getUsername();
    this.givenName = user.getGivenName();
    this.familyName = user.getFamilyName();
    this.phoneNumber = user.getPhone();
  }
}
