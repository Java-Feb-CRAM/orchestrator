/**
 * 
 */
package com.smoothstack.utopia.orchestrator.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.utopia.orchestrator.util.ForwardUtil;

/**
 * @author Craig Saunders
 *
 */
@RestController
@Secured("ROLE_USER")
public class PasswordResetController {
    @Autowired
    RestTemplate restTemplate;

    private final String MAPPING_VALUE = "/password-reset";
    private final String BASE_URI = "http://user-auth-service/registration";
    
    @PostMapping(path = MAPPING_VALUE)
    public ResponseEntity<String> registerUserAccount(HttpServletRequest request) 
    {
        return ForwardUtil.forwardRequest(
            restTemplate, 
            request, 
            BASE_URI);
    }
    
    @GetMapping(path = MAPPING_VALUE + "/reset-password-token-link/{username}")
    public ResponseEntity<String> resetPasswordTokenLink(HttpServletRequest request, 
            @PathVariable("username") String username) 
    {
        return ForwardUtil.forwardRequest(
            restTemplate, 
            request, 
            BASE_URI+"/reset-password-token-link/{username}",
            username);
    }
    
    @GetMapping(path = MAPPING_VALUE + "confirm-password-token/{token}")
    public ResponseEntity<String> confirmPasswordToken(HttpServletRequest request, 
            @PathVariable("token") String token) 
    {
        return ForwardUtil.forwardRequest(
            restTemplate, 
            request, 
            BASE_URI+"confirm-password-token/{token}",
            token);
    }
}
