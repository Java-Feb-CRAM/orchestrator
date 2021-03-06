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
 * Mar 17 2021
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/airports")
public class AirportController {

  @Autowired
  RestTemplate restTemplate;

  private static final String AIRPORTS_URL =
    "http://flight-plane-service/airports";
  private static final String AIRPORTS_ID_URL = AIRPORTS_URL + "/{id}";

  @GetMapping
  public ResponseEntity<String> getAllAirports(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(restTemplate, request, AIRPORTS_URL);
  }

  @GetMapping(path = "{airportId}")
  public ResponseEntity<String> getAirport(
    HttpServletRequest request,
    @PathVariable("airportId") String airportId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      AIRPORTS_ID_URL,
      airportId
    );
  }

  @PostMapping
  @Secured("ROLE_ADMIN")
  public ResponseEntity<String> createAirport(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(restTemplate, request, AIRPORTS_URL);
  }

  @PutMapping(path = "{airportId}")
  @Secured("ROLE_ADMIN")
  public ResponseEntity<String> updateAirport(
    HttpServletRequest request,
    @PathVariable("airportId") String airportId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      AIRPORTS_ID_URL,
      airportId
    );
  }

  @DeleteMapping(path = "{airportId}")
  @Secured("ROLE_ADMIN")
  public ResponseEntity<String> deleteAirport(
    HttpServletRequest request,
    @PathVariable("airportId") String airportId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      AIRPORTS_ID_URL,
      airportId
    );
  }
}
