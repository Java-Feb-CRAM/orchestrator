/**
 *
 */
package com.smoothstack.utopia.orchestrator.controller;

import com.smoothstack.utopia.orchestrator.util.ForwardUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Craig Saunders
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class PasswordResetController {

  @Autowired
  RestTemplate restTemplate;

  private static final String BASE_URI = "http://user-auth-service";
  private static final String NEW_PASSWORD = "/users/password/new";
  private static final String GENERATE_TOKEN =
    "/users/password/tokens/generate";
  private static final String CONFIRM_TOKEN = "/users/password/tokens/confirm";

  @PostMapping(path = NEW_PASSWORD)
  public ResponseEntity<String> changeUserPassword(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      BASE_URI + NEW_PASSWORD
    );
  }

  @PostMapping(path = GENERATE_TOKEN)
  public ResponseEntity<String> generatePasswordResetToken(
    HttpServletRequest request
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      BASE_URI + GENERATE_TOKEN
    );
  }

  @PostMapping(path = CONFIRM_TOKEN)
  public ResponseEntity<String> confirmPasswordToken(
    HttpServletRequest request
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      BASE_URI + CONFIRM_TOKEN
    );
  }
}
