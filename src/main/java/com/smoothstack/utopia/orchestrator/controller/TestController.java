package com.smoothstack.utopia.orchestrator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rob Maes
 * Mar 19 2021
 */
@RestController
@RequestMapping("/public")
public class TestController {

  @GetMapping
  public String hello() {
    return "hello world";
  }
}
