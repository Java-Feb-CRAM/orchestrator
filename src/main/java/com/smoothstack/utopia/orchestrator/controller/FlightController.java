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

  private static final String FLIGHTS_URL =
    "http://flight-plane-service/flights";
  private static final String FLIGHTS_ID_URL = FLIGHTS_URL + "/{id}";
  private static final String FLIGHT_SEARCH_URI =
    FLIGHTS_URL +
    "/origin/{originIataId}/destination/{destinationIataId}/from/{dateRangeStart}/to/{dateRangeEnd}/search/{stops}";

  @GetMapping(
    path = "origin/{originIataId}/destination/{destinationIataId}/from/{dateRangeStart}/to/{dateRangeEnd}/search/{stops}"
  )
  public ResponseEntity<String> getAllMultiStopFlights(
    HttpServletRequest request,
    @PathVariable("originIataId") String originIataId,
    @PathVariable("destinationIataId") String destinationIataId,
    @PathVariable("dateRangeStart") Long dateRangeStart,
    @PathVariable("dateRangeEnd") Long dateRangeEnd,
    @PathVariable("stops") Integer stops
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      FLIGHT_SEARCH_URI,
      originIataId,
      destinationIataId,
      dateRangeStart,
      dateRangeEnd,
      stops
    );
  }

  @GetMapping
  public ResponseEntity<String> getAllFlights(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(restTemplate, request, FLIGHTS_URL);
  }

  @GetMapping(path = "{flightId}")
  public ResponseEntity<String> getFlight(
    HttpServletRequest request,
    @PathVariable("flightId") String flightId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      FLIGHTS_ID_URL,
      flightId
    );
  }

  @Secured("ROLE_ADMIN")
  @PostMapping
  public ResponseEntity<String> createFlight(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(restTemplate, request, FLIGHTS_URL);
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
      FLIGHTS_ID_URL,
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
      FLIGHTS_ID_URL,
      flightId
    );
  }
}
