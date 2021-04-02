/**
 * 
 */
package com.smoothstack.utopia.orchestrator.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.common.net.HttpHeaders;
import com.smoothstack.utopia.orchestrator.dao.UserRepository;
import com.smoothstack.utopia.orchestrator.dto.UserInfoDto;
import com.smoothstack.utopia.orchestrator.security.JwtUtil;
import com.smoothstack.utopia.orchestrator.util.ForwardUtil;
import com.smoothstack.utopia.orchestrator.security.InvalidTokenException;

/**
 * @author Craig Saunders
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class AuthenticationController {

    private final String BASE_URI = "http://user-auth-service";
    private final String AUTHENTICATE_USER = "/users/credentials/authenticate";
    private final String CURRENT_USER = "/users/current";
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping(path = AUTHENTICATE_USER)
    public ResponseEntity<String> authenticateUser(HttpServletRequest request)
    {
        return ForwardUtil.forwardRequest(
            restTemplate, 
            request, 
            BASE_URI+AUTHENTICATE_USER);
    }
    
    @GetMapping(path = CURRENT_USER)
    @Secured("ROLE_USER")
    public UserInfoDto getCurrentUser(HttpServletRequest request)
    {
		return new UserInfoDto(userRepository.findByUsername(
				jwtUtil.parseUsernameFromJwt(
						request.getHeader(HttpHeaders.AUTHORIZATION)
						.split(" ")[1].trim()))
				.orElseThrow(InvalidTokenException::new));
    }
}
