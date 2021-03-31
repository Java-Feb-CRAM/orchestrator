/**
 * 
 */
package com.smoothstack.utopia.orchestrator.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.utopia.orchestrator.util.ForwardUtil;

/**
 * @author Craig Saunders
 *
 */
@RestController
public class RegistrationController {
    @Autowired
    RestTemplate restTemplate;
    
    private final String MAPPING_VALUE = "/users";
    private final String BASE_URI = "http://user-auth-service";
    private final String NEW_USER = MAPPING_VALUE + "/new";
    private final String GENERATE_TOKEN = MAPPING_VALUE + "/usernames/tokens/generate";
    private final String ACTIVATE_USER = MAPPING_VALUE + "/useranames/tokens/activate";

    @PostMapping(path = NEW_USER)
    public ResponseEntity<String> registerUserAccount(HttpServletRequest request)
    {
        return ForwardUtil.forwardRequest(
            restTemplate, 
            request, 
            BASE_URI+NEW_USER);
    }

    @PostMapping(path = GENERATE_TOKEN)
    public ResponseEntity<String> generateVerificationToken(HttpServletRequest request) 
    {
        return ForwardUtil.forwardRequest(
            restTemplate, 
            request, 
            BASE_URI+GENERATE_TOKEN);
    }

    @PostMapping(path = ACTIVATE_USER)
    public ResponseEntity<String> verifyEmail(HttpServletRequest request) {
        return ForwardUtil.forwardRequest(
            restTemplate, 
            request, 
            BASE_URI+ACTIVATE_USER);
    }
}
