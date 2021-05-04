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
public class RegistrationController {

  @Autowired
  RestTemplate restTemplate;

  private static final String BASE_URI = "http://user-auth-service";
  private static final String NEW_USER = "/users/new";
  private static final String GENERATE_TOKEN =
    "/users/usernames/tokens/generate";
  private static final String ACTIVATE_USER =
    "/users/usernames/tokens/activate";

  @PostMapping(path = NEW_USER)
  public ResponseEntity<String> registerUserAccount(
    HttpServletRequest request
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      BASE_URI + NEW_USER
    );
  }

  @PostMapping(path = GENERATE_TOKEN)
  public ResponseEntity<String> generateVerificationToken(
    HttpServletRequest request
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      BASE_URI + GENERATE_TOKEN
    );
  }

  @PostMapping(path = ACTIVATE_USER)
  public ResponseEntity<String> verifyEmail(HttpServletRequest request) {
    System.out.println("activate");
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      BASE_URI + ACTIVATE_USER
    );
  }
}
