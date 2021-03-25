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
public class AuthenticationController {

    private final String MAPPING_VALUE = "/authentication";
    private final String BASE_URI = "http://user-auth-service"+MAPPING_VALUE;
    @Autowired
    RestTemplate restTemplate;

    @PostMapping(path = MAPPING_VALUE)
    public ResponseEntity<String> authenticateUser(HttpServletRequest request)
    {
        return ForwardUtil.forwardRequest(
            restTemplate, 
            request, 
            BASE_URI);
    }
}
