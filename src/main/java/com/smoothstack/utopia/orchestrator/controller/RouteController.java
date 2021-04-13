package com.smoothstack.utopia.orchestrator.controller;

import com.smoothstack.utopia.orchestrator.util.ForwardUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Rob Maes
 * Mar 20 2021
 */
@RestController
@RequestMapping("/routes")
@Secured("ROLE_ADMIN")
public class RouteController {

  @Autowired
  RestTemplate restTemplate;

  private static final String ROUTES_URL = "http://flight-plane-service/routes";
  private static final String ROUTES_ID_URL = ROUTES_URL + "/{id}";

  @GetMapping
  public ResponseEntity<String> getAllRoutes(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(restTemplate, request, ROUTES_URL);
  }

  @GetMapping(path = "{routeId}")
  public ResponseEntity<String> getRoute(
    HttpServletRequest request,
    @PathVariable("routeId") String routeId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      ROUTES_ID_URL,
      routeId
    );
  }

  @PostMapping
  public ResponseEntity<String> createRoute(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(restTemplate, request, ROUTES_URL);
  }

  @PutMapping(path = "{routeId}")
  public ResponseEntity<String> updateRoute(
    HttpServletRequest request,
    @PathVariable("routeId") String routeId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      ROUTES_ID_URL,
      routeId
    );
  }

  @DeleteMapping(path = "{routeId}")
  public ResponseEntity<String> deleteRoute(
    HttpServletRequest request,
    @PathVariable("routeId") String routeId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      ROUTES_ID_URL,
      routeId
    );
  }
}
