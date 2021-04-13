package com.smoothstack.utopia.orchestrator.util;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
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

  private ForwardUtil() {
    throw new IllegalStateException("Utility class");
  }

  public static ResponseEntity<String> forwardRequest(
    RestTemplate rt,
    HttpServletRequest request,
    String url,
    Object... pathVariable
  ) {
    // construct headers for the outgoing request
    HttpHeaders headers = new HttpHeaders();
    // see if incoming request includes Content-Type header
    Optional<String> contentType = Optional.ofNullable(
      request.getHeader("Content-Type")
    );
    // see if incoming request includes Accept header
    Optional<String> accept = Optional.ofNullable(request.getHeader("Accept"));
    // if the incoming request has Content-Type header, add it to the outgoing request
    contentType.ifPresent(
      s -> headers.setContentType(MediaType.parseMediaType(s))
    );
    // if the incoming request has Accept header, add it to the outgoing request
    // if not, have outgoing request accept everything
    accept.ifPresentOrElse(
      s -> headers.setAccept(MediaType.parseMediaTypes(s)),
      () -> headers.setAccept(List.of(MediaType.ALL))
    );
    // set http method of outgoing request to that of the incoming request
    HttpMethod method = HttpMethod.resolve(request.getMethod());
    // create http entity for the outgoing request
    HttpEntity<String> entity;
    // if the incoming request is a POST or PUT
    if (
      method != null &&
      (method.equals(HttpMethod.POST) || method.equals(HttpMethod.PUT))
    ) {
      // attempt to parse the incoming request's body as a string
      String body = "";
      try {
        body =
          request
            .getReader()
            .lines()
            .collect(Collectors.joining(System.lineSeparator()));
      } catch (IOException ignored) {
        // empty body
      }
      // if the body is empty, do not include it in the entity
      if (body.equals("")) {
        entity = new HttpEntity<>(headers);
      } else {
        // if the body is not empty, include it in the entity
        entity = new HttpEntity<>(body, headers);
      }
    } else {
      // if the incoming request is not POST or PUT, do not include the body in the entity
      entity = new HttpEntity<>(headers);
    }
    try {
      if (method == null) {
        method = HttpMethod.GET;
      }
      // attempt to send the outgoing request and return it's response
      return rt.exchange(url, method, entity, String.class, pathVariable);
    } catch (HttpClientErrorException e) {
      // if the request fails, return the failing status code and body
      return new ResponseEntity<>(
        e.getResponseBodyAsString(),
        e.getStatusCode()
      );
    }
  }
}
