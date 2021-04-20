package com.smoothstack.utopia.orchestrator.controller;

import com.smoothstack.utopia.orchestrator.util.ForwardUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Rob Maes
 * Apr 14 2021
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bookings")
public class BookingController {

  @Autowired
  RestTemplate restTemplate;

  private static final String BOOKINGS_URL =
    "http://ticket-payment-service/bookings";
  private static final String BOOKINGS_ID_URL = BOOKINGS_URL + "/{id}";

  @GetMapping
  @Secured("ROLE_ADMIN")
  public ResponseEntity<String> getAllBookings(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(restTemplate, request, BOOKINGS_URL);
  }

  @GetMapping(path = "{bookingId}")
  public ResponseEntity<String> getBooking(
    HttpServletRequest request,
    @PathVariable("bookingId") String bookingId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      BOOKINGS_ID_URL,
      bookingId
    );
  }

  @GetMapping(path = "/confirmation/{confirmationCode}")
  public ResponseEntity<String> getBookingByConfirmationCode(
    HttpServletRequest request,
    @PathVariable("confirmationCode") String confirmationCode
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      BOOKINGS_URL + "/confirmation/{id}",
      confirmationCode
    );
  }

  @GetMapping(path = "/user/{userId}")
  public ResponseEntity<String> getBookingsByUser(
    HttpServletRequest request,
    @PathVariable("userId") Long userId
  ) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      BOOKINGS_URL + "/user/{id}",
      userId
    );
  }

  @PostMapping(path = "/guest")
  public ResponseEntity<String> createGuestBooking(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      BOOKINGS_URL + "/guest"
    );
  }

  @PostMapping(path = "/user")
  public ResponseEntity<String> createUserBooking(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      BOOKINGS_URL + "/user"
    );
  }

  @PostMapping(path = "/agent")
  public ResponseEntity<String> createAgentBooking(HttpServletRequest request) {
    return ForwardUtil.forwardRequest(
      restTemplate,
      request,
      BOOKINGS_URL + "/agent"
    );
  }
}
