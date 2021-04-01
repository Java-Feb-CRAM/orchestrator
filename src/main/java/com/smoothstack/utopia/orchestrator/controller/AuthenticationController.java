/**
 *
 */
package com.smoothstack.utopia.orchestrator.controller;

import com.smoothstack.utopia.orchestrator.dao.UserRepository;
import com.smoothstack.utopia.orchestrator.dto.UserInfoDto;
import com.smoothstack.utopia.orchestrator.util.ForwardUtil;
import com.smoothstack.utopia.shared.model.User;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Craig Saunders
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class AuthenticationController {

  private final String BASE_URI = "http://user-auth-service";
  private final String AUTHENTICATE_USER = "/users/credentails/authenticate";

  @Autowired
  private UserRepository userRepository;

  @Autowired
  RestTemplate restTemplate;

  @PostMapping(path = AUTHENTICATE_USER)
  public ResponseEntity<String> authenticateUser(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      BASE_URI + AUTHENTICATE_USER
    );
  }

  @Secured({ "ROLE_ADMIN", "ROLE_AGENT", "ROLE_USER" })
  @GetMapping("/users/me")
  public UserInfoDto getAuthenticatedUser(Principal principal) {
    User user = userRepository
      .findByUsername(principal.getName())
      .orElseThrow(RuntimeException::new);
    return new UserInfoDto(user);
  }
}
