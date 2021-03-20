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
@RequestMapping("/airplane_types")
@Secured("ROLE_ADMIN")
public class AirplaneTypeController {

  @Autowired
  RestTemplate restTemplate;

  @GetMapping
  public ResponseEntity<String> getAllAirplaneTypes(
    HttpServletRequest request
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/airplane_types"
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
      "http://flight-plane-service/airplane_types/{id}",
      airplaneTypeId
    );
  }

  @PostMapping
  public ResponseEntity<String> createAirplaneType(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/airplane_types"
    );
  }

  @PutMapping(path = "{airplaneTypeId}")
  public ResponseEntity<String> updateAirplaneType(
    HttpServletRequest request,
    @PathVariable("airplaneTypeId") String airplaneTypeId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/airplane_types/{id}",
      airplaneTypeId
    );
  }

  @DeleteMapping(path = "{airplaneTypeId}")
  public ResponseEntity<String> deleteAirplaneType(
    HttpServletRequest request,
    @PathVariable("airplaneTypeId") String airplaneTypeId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/airplane_types/{id}",
      airplaneTypeId
    );
  }
}
