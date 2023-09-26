package vsvdev.co.ua.micro.service;

import vsvdev.co.ua.micro.entities.Greeting;

public interface GreetingService {

  Greeting greet(String name);
}