package com.smoothstack.utopia.orchestrator.controller;

import com.smoothstack.utopia.orchestrator.util.ForwardUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Rob Maes
 * May 10 2021
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/seat_layouts")
public class SeatLayoutController {

  @Autowired
  RestTemplate restTemplate;

  private static final String SEAT_LAYOUTS_URL =
    "http://flight-plane-service/seat_layouts";
  private static final String SEAT_LAYOUTS_ID_URL = SEAT_LAYOUTS_URL + "/{id}";

  @GetMapping
  public ResponseEntity<String> getAllSeatLayouts(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(restTemplate, request, SEAT_LAYOUTS_URL);
  }

  @GetMapping(path = "{seatLayoutId}")
  public ResponseEntity<String> getSeatLayout(
    HttpServletRequest request,
    @PathVariable("seatLayoutId") String seatLayoutId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      SEAT_LAYOUTS_ID_URL,
      seatLayoutId
    );
  }
}
