package com.smoothstack.utopia.orchestrator.controller;

import com.netflix.discovery.converters.Auto;
import com.smoothstack.utopia.orchestrator.util.ForwardUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class RouteController {

  @Autowired
  RestTemplate restTemplate;

  @GetMapping
  public ResponseEntity<String> getAllRoutes(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/routes"
    );
  }

  @GetMapping(path = "{routeId}")
  public ResponseEntity<String> getRoute(
    HttpServletRequest request,
    @PathVariable("routeId") String routeId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/routes/{id}",
      routeId
    );
  }

  @PostMapping
  public ResponseEntity<String> createRoute(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/routes"
    );
  }

  @PutMapping(path = "{routeId}")
  public ResponseEntity<String> updateRoute(
    HttpServletRequest request,
    @PathVariable("routeId") String routeId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/routes/{id}",
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
      "http://flight-plane-service/routes/{id}",
      routeId
    );
  }
}
