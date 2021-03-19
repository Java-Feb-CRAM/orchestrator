package com.smoothstack.utopia.orchestrator.controller;

import com.smoothstack.utopia.orchestrator.util.ForwardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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
 * Mar 18 2021
 */
@RestController
@RequestMapping("/flights")
public class FlightController {

  @Autowired
  RestTemplate restTemplate;

  @GetMapping
  public ResponseEntity<String> getAllFlights(
    @RequestHeader("Content-Type") String contentType,
    @RequestHeader("Accept") String accept
  ) {
    return ForwardUtil.forwardRequest(
      contentType,
      accept,
      restTemplate,
      "http://flight-plane-service/flights",
      HttpMethod.GET
    );
  }

  @GetMapping(path = "{flightId}")
  public ResponseEntity<String> getFlight(
    @RequestHeader("Content-Type") String contentType,
    @RequestHeader("Accept") String accept,
    @PathVariable("flightId") String flightId
  ) {
    return ForwardUtil.forwardRequest(
      contentType,
      accept,
      restTemplate,
      "http://flight-plane-service/flights/{id}",
      flightId,
      HttpMethod.GET
    );
  }

  @PostMapping
  public ResponseEntity<String> createFlight(
    @RequestHeader("Content-Type") String contentType,
    @RequestHeader("Accept") String accept,
    @RequestBody String body
  ) {
    return ForwardUtil.forwardRequest(
      contentType,
      accept,
      restTemplate,
      "http://flight-plane-service/flights",
      HttpMethod.POST,
      body
    );
  }

  @PutMapping(path = "{flightId}")
  public ResponseEntity<String> updateFlight(
    @RequestHeader("Content-Type") String contentType,
    @RequestHeader("Accept") String accept,
    @PathVariable("flightId") String flightId,
    @RequestBody String body
  ) {
    return ForwardUtil.forwardRequest(
      contentType,
      accept,
      restTemplate,
      "http://flight-plane-service/flights/{id}",
      flightId,
      HttpMethod.PUT,
      body
    );
  }

  @DeleteMapping(path = "{flightId}")
  public ResponseEntity<String> deleteFlight(
    @RequestHeader("Content-Type") String contentType,
    @RequestHeader("Accept") String accept,
    @PathVariable("flightId") String flightId
  ) {
    return ForwardUtil.forwardRequest(
      contentType,
      accept,
      restTemplate,
      "http://flight-plane-service/flights/{id}",
      flightId,
      HttpMethod.DELETE
    );
  }
}
