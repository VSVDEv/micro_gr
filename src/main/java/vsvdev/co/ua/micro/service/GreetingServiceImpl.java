package vsvdev.co.ua.micro.service;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;
import vsvdev.co.ua.micro.entities.Greeting;

@Service
public class GreetingServiceImpl implements GreetingService {

  private static final String TEMPLATE = "Hello, %s!";
  private static final AtomicLong COUNTER = new AtomicLong();

  @Override
  public Greeting greet(String name) {
    return new Greeting(COUNTER.incrementAndGet(), String.format(TEMPLATE, name));
  }
}