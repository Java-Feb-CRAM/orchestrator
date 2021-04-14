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
 * Apr 14 2021
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/payments")
public class PaymentController {

  @Autowired
  RestTemplate restTemplate;

  private static final String PAYMENTS_ID_URL =
    "http://ticket-payment-service/payments";

  @GetMapping(path = "{stripeId}")
  public ResponseEntity<String> getPayment(
    HttpServletRequest request,
    @PathVariable("stripeId") String stripeId
  ) {
    return ForwardUtil.forwardRequest(restTemplate, request, PAYMENTS_ID_URL);
  }
}
