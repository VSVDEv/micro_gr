package vsvdev.co.ua.micro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import vsvdev.co.ua.micro.entities.Greeting;



public class GreetingServiceTest {

  private transient final GreetingService service = new GreetingServiceImpl();


  @Test
  public void shouildReturnName() throws Exception {

    // given
    String expectedContent = "Hello, sv!";
    long expectedId = service.greet("").getId() + 1L;

    // when
    Greeting greeting = service.greet("sv");

    // then
    assertEquals(expectedId, greeting.getId());
    assertEquals(expectedContent, greeting.getContent());
    System.out.println("******************************TEST******************");
  }

  @Test
  public void shouildReturnCount() {

    // given
    long expectedId = service.greet("").getId() + 1L;
    String expectedContent = "Hello, !";

    // when
    Greeting greeting = service.greet("");

    // then
    assertEquals(expectedId, greeting.getId());
    assertEquals(expectedContent,greeting.getContent());
  }

}
