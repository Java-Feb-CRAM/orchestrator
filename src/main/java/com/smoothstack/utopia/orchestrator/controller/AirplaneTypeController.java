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
 * Mar 20 2021
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/airplane_types")
public class AirplaneTypeController {

  @Autowired
  RestTemplate restTemplate;

  private static final String AIRPLANE_TYPES_URL =
    "http://flight-plane-service/airplane_types";
  private static final String AIRPLANE_TYPES_ID_URL =
    AIRPLANE_TYPES_URL + "/{id}";

  @GetMapping
  public ResponseEntity<String> getAllAirplaneTypes(
    HttpServletRequest request
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      AIRPLANE_TYPES_URL
    );
  }

  @GetMapping(path = "{airplaneTypeId}")
  public ResponseEntity<String> getAirplaneType(
    HttpServletRequest request,
    @PathVariable("airplaneTypeId") String airplaneTypeId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      AIRPLANE_TYPES_ID_URL,
      airplaneTypeId
    );
  }

  @PostMapping
  @Secured("ROLE_ADMIN")
  public ResponseEntity<String> createAirplaneType(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      AIRPLANE_TYPES_URL
    );
  }

  @PutMapping(path = "{airplaneTypeId}")
  @Secured("ROLE_ADMIN")
  public ResponseEntity<String> updateAirplaneType(
    HttpServletRequest request,
    @PathVariable("airplaneTypeId") String airplaneTypeId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      AIRPLANE_TYPES_ID_URL,
      airplaneTypeId
    );
  }

  @DeleteMapping(path = "{airplaneTypeId}")
  @Secured("ROLE_ADMIN")
  public ResponseEntity<String> deleteAirplaneType(
    HttpServletRequest request,
    @PathVariable("airplaneTypeId") String airplaneTypeId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      AIRPLANE_TYPES_ID_URL,
      airplaneTypeId
    );
  }
}
