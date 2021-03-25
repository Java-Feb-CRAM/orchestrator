/**
 * 
 */
package com.smoothstack.utopia.orchestrator.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class RegistrationController {
    @Autowired
    RestTemplate restTemplate;
    
    private final String MAPPING_VALUE = "/registration";
    private final String BASE_URI = "http://user-auth-service"+MAPPING_VALUE;

    @PostMapping(path = MAPPING_VALUE)
    public ResponseEntity<String> registerUserAccount(HttpServletRequest request)
    {
        return ForwardUtil.forwardRequest(
            restTemplate, 
            request, 
            BASE_URI);
    }

    @GetMapping(path = MAPPING_VALUE+"/email-verification-resend/{username}")
    public ResponseEntity<String> resendEmailVerificationToken(HttpServletRequest request, 
            @PathVariable("username") String username) 
    {
        return ForwardUtil.forwardRequest(
            restTemplate, 
            request, 
            BASE_URI+"/email-verification-resend/{username}",
            username);
    }

    @GetMapping(path = MAPPING_VALUE+"/email-verification/{token}")
    public ResponseEntity<String> verifyEmail(HttpServletRequest request, @PathVariable("token") String token) {
        return ForwardUtil.forwardRequest(
            restTemplate, 
            request, 
            BASE_URI+"/email-verification/{token}",
            token);
    }
}
