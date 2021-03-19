package com.smoothstack.utopia.orchestrator.controller;

import com.smoothstack.utopia.orchestrator.util.ForwardUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Rob Maes
 * Mar 17 2021
 */
@RestController
@RequestMapping("/airports")
public class AirportController {

  @Autowired
  RestTemplate restTemplate;

  @GetMapping
  public ResponseEntity<String> getAllAirports(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/airports"
    );
  }

  @GetMapping(path = "{airportId}")
  public ResponseEntity<String> getAirport(
    HttpServletRequest request,
    @PathVariable("airportId") String airportId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/airports/{id}",
      airportId
    );
  }

  @PostMapping
  public ResponseEntity<String> createAirport(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/airports"
    );
  }

  @PutMapping(path = "{airportId}")
  public ResponseEntity<String> updateAirport(
    HttpServletRequest request,
    @PathVariable("airportId") String airportId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/airports/{id}",
      airportId
    );
  }

  @DeleteMapping(path = "{airportId}")
  public ResponseEntity<String> deleteAirport(
    HttpServletRequest request,
    @PathVariable("airportId") String airportId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/airports/{id}",
      airportId
    );
  }
}
