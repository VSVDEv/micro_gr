package vsvdev.co.ua.micro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vsvdev.co.ua.micro.entities.Greeting;
import vsvdev.co.ua.micro.service.GreetingService;


@RestController
public class GreetingController {
  private final GreetingService service;

  private static final Logger log = LoggerFactory.getLogger(GreetingController.class);


  public GreetingController(GreetingService service) {
    this.service = service;
  }

  @GetMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

    try {
      if ("admin".equals(name)) {
        throw new IllegalArgumentException("this value is prohibited");
      }
    } catch (IllegalArgumentException ex) {
      log.error("Exception {}", ex.getMessage());
    }

    log.info("!!!!! Request with name {}", name);
    Greeting greet = service.greet(name);
    log.info("!!!!! Response with content {} and id {}", greet.getContent(), greet.getId());
    return greet;
  }
}