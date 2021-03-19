package com.smoothstack.utopia.orchestrator.util;

import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Rob Maes
 * Mar 17 2021
 */
public class ForwardUtil {

  public static ResponseEntity<String> forwardRequest(
    String contentType,
    String accept,
    RestTemplate rt,
    String url,
    Object variable,
    HttpMethod method
  ) {
    if (
      !contentType.equals("application/xml") &&
      !contentType.equals("application/json")
    ) {
      throw new ResponseStatusException(
        HttpStatus.UNSUPPORTED_MEDIA_TYPE,
        "Request must be XML or JSON"
      );
    }
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(
      contentType.equals("application/xml")
        ? MediaType.APPLICATION_XML
        : MediaType.APPLICATION_JSON
    );
    headers.setAccept(
      accept.equals("application/xml")
        ? List.of(MediaType.APPLICATION_XML)
        : List.of(MediaType.APPLICATION_JSON)
    );
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
    String contentType,
    String accept,
    RestTemplate rt,
    String url,
    HttpMethod method
  ) {
    if (
      !contentType.equals("application/xml") &&
      !contentType.equals("application/json")
    ) {
      throw new ResponseStatusException(
        HttpStatus.UNSUPPORTED_MEDIA_TYPE,
        "Request must be XML or JSON"
      );
    }
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(
      contentType.equals("application/xml")
        ? MediaType.APPLICATION_XML
        : MediaType.APPLICATION_JSON
    );
    headers.setAccept(
      accept.equals("application/xml")
        ? List.of(MediaType.APPLICATION_XML)
        : List.of(MediaType.APPLICATION_JSON)
    );
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
    String contentType,
    String accept,
    RestTemplate rt,
    String url,
    HttpMethod method,
    String body
  ) {
    if (
      !contentType.equals("application/xml") &&
      !contentType.equals("application/json")
    ) {
      throw new ResponseStatusException(
        HttpStatus.UNSUPPORTED_MEDIA_TYPE,
        "Request must be XML or JSON"
      );
    }
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(
      contentType.equals("application/xml")
        ? MediaType.APPLICATION_XML
        : MediaType.APPLICATION_JSON
    );
    headers.setAccept(
      accept.equals("application/xml")
        ? List.of(MediaType.APPLICATION_XML)
        : List.of(MediaType.APPLICATION_JSON)
    );
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
    String contentType,
    String accept,
    RestTemplate rt,
    String url,
    Object variable,
    HttpMethod method,
    String body
  ) {
    if (
      !contentType.equals("application/xml") &&
      !contentType.equals("application/json")
    ) {
      throw new ResponseStatusException(
        HttpStatus.UNSUPPORTED_MEDIA_TYPE,
        "Request must be XML or JSON"
      );
    }
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(
      contentType.equals("application/xml")
        ? MediaType.APPLICATION_XML
        : MediaType.APPLICATION_JSON
    );
    headers.setAccept(
      accept.equals("application/xml")
        ? List.of(MediaType.APPLICATION_XML)
        : List.of(MediaType.APPLICATION_JSON)
    );
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
