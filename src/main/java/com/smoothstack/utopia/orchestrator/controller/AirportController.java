package com.smoothstack.utopia.orchestrator.controller;

import com.smoothstack.utopia.orchestrator.util.ForwardUtil;
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
  public ResponseEntity<String> getAllAirports(
    @RequestHeader("Content-Type") String contentType,
    @RequestHeader("Accept") String accept
  ) {
    return ForwardUtil.forwardRequest(
      contentType,
      accept,
      restTemplate,
      "http://flight-plane-service/airports",
      HttpMethod.GET
    );
  }

  @GetMapping(path = "{airportId}")
  public ResponseEntity<String> getAirport(
    @RequestHeader("Content-Type") String contentType,
    @RequestHeader("Accept") String accept,
    @PathVariable("airportId") String airportId
  ) {
    return ForwardUtil.forwardRequest(
      contentType,
      accept,
      restTemplate,
      "http://flight-plane-service/airports/{id}",
      airportId,
      HttpMethod.GET
    );
  }

  @PostMapping
  public ResponseEntity<String> createAirport(
    @RequestHeader("Content-Type") String contentType,
    @RequestHeader("Accept") String accept,
    @RequestBody String body
  ) {
    return ForwardUtil.forwardRequest(
      contentType,
      accept,
      restTemplate,
      "http://flight-plane-service/airports",
      HttpMethod.POST,
      body
    );
  }

  @PutMapping(path = "{airportId}")
  public ResponseEntity<String> updateAirport(
    @RequestHeader("Content-Type") String contentType,
    @RequestHeader("Accept") String accept,
    @PathVariable("airportId") String airportId,
    @RequestBody String body
  ) {
    return ForwardUtil.forwardRequest(
      contentType,
      accept,
      restTemplate,
      "http://flight-plane-service/airports/{id}",
      airportId,
      HttpMethod.PUT,
      body
    );
  }

  @DeleteMapping(path = "{airportId}")
  public ResponseEntity<String> deleteAirport(
    @RequestHeader("Content-Type") String contentType,
    @RequestHeader("Accept") String accept,
    @PathVariable("airportId") String airportId
  ) {
    return ForwardUtil.forwardRequest(
      contentType,
      accept,
      restTemplate,
      "http://flight-plane-service/airports/{id}",
      airportId,
      HttpMethod.DELETE
    );
  }
}
