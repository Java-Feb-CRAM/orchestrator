package com.smoothstack.utopia.orchestrator.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Rob Maes
 * Mar 17 2021
 */
public class ForwardUtil {

  public static ResponseEntity<String> forwardRequest(
    RestTemplate rt,
    String url,
    Object variable,
    HttpMethod method
  ) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<>(headers);
    try {
      return rt.exchange(url, method, entity, String.class, variable);
    } catch (HttpClientErrorException e) {
      return new ResponseEntity<>(
        e.getResponseBodyAsString(),
        e.getStatusCode()
      );
    }
  }

  public static ResponseEntity<String> forwardRequest(
    RestTemplate rt,
    String url,
    HttpMethod method
  ) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<>(headers);
    try {
      return rt.exchange(url, method, entity, String.class);
    } catch (HttpClientErrorException e) {
      return new ResponseEntity<>(
        e.getResponseBodyAsString(),
        e.getStatusCode()
      );
    }
  }

  public static ResponseEntity<String> forwardRequest(
    RestTemplate rt,
    String url,
    HttpMethod method,
    String body
  ) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<>(body, headers);
    try {
      return rt.exchange(url, method, entity, String.class);
    } catch (HttpClientErrorException e) {
      return new ResponseEntity<>(
        e.getResponseBodyAsString(),
        e.getStatusCode()
      );
    }
  }

  public static ResponseEntity<String> forwardRequest(
    RestTemplate rt,
    String url,
    Object variable,
    HttpMethod method,
    String body
  ) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<>(body, headers);
    try {
      return rt.exchange(url, method, entity, String.class, variable);
    } catch (HttpClientErrorException e) {
      return new ResponseEntity<>(
        e.getResponseBodyAsString(),
        e.getStatusCode()
      );
    }
  }
}
