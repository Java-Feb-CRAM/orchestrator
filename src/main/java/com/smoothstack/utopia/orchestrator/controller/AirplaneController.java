package com.smoothstack.utopia.orchestrator.controller;

import com.smoothstack.utopia.orchestrator.util.ForwardUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/airplanes")
@Secured("ROLE_ADMIN")
public class AirplaneController {

  @Autowired
  RestTemplate restTemplate;

  @GetMapping
  public ResponseEntity<String> getAllAirplanes(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/airplanes"
    );
  }

  @GetMapping(path = "{airplaneId}")
  public ResponseEntity<String> getAirplane(
    HttpServletRequest request,
    @PathVariable("airplaneId") String airplaneId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/airplanes/{id}",
      airplaneId
    );
  }

  @PostMapping
  public ResponseEntity<String> createAirplane(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/airplanes"
    );
  }

  @PutMapping(path = "{airplaneId}")
  public ResponseEntity<String> updateAirplane(
    HttpServletRequest request,
    @PathVariable("airplaneId") String airplaneId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/airplanes/{id}",
      airplaneId
    );
  }

  @DeleteMapping(path = "{airplaneId}")
  public ResponseEntity<String> deleteAirplane(
    HttpServletRequest request,
    @PathVariable("airplaneId") String airplaneId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      "http://flight-plane-service/airplanes/{id}",
      airplaneId
    );
  }
}
