package com.smoothstack.utopia.orchestrator.controller;

import com.smoothstack.utopia.orchestrator.util.ForwardUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
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
 * Mar 18 2021
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/flights")
public class FlightController {

  @Autowired
  RestTemplate restTemplate;

  @GetMapping
  public ResponseEntity<String> getAllFlights(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/flights"
    );
  }

  @GetMapping(path = "{flightId}")
  public ResponseEntity<String> getFlight(
    HttpServletRequest request,
    @PathVariable("flightId") String flightId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/flights/{id}",
      flightId
    );
  }

  @Secured("ROLE_ADMIN")
  @PostMapping
  public ResponseEntity<String> createFlight(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/flights"
    );
  }

  @Secured("ROLE_ADMIN")
  @PutMapping(path = "{flightId}")
  public ResponseEntity<String> updateFlight(
    HttpServletRequest request,
    @PathVariable("flightId") String flightId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/flights/{id}",
      flightId
    );
  }

  @Secured("ROLE_ADMIN")
  @DeleteMapping(path = "{flightId}")
  public ResponseEntity<String> deleteFlight(
    HttpServletRequest request,
    @PathVariable("flightId") String flightId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/flights/{id}",
      flightId
    );
  }
}
