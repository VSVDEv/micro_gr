package vsvdev.co.ua.micro.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import vsvdev.co.ua.micro.entities.Greeting;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GreetingControllerIT {

  @Autowired
  private transient MockMvc mvc;

  private transient final ObjectMapper mapper = new ObjectMapper();


  @Test
  @Order(1)
  public void shouildReturnName() throws Exception {

    // given
    String expectedContent = "Hello, sv!";
    String res = mvc.perform(get("/greeting")).andReturn().getResponse().getContentAsString();
    Greeting response = mapper.readValue(res, Greeting.class);
    long expectedId;
    if (response != null) {
      expectedId = response.getId() + 1L;
    } else {
      expectedId = 0L;
    }

    // when
    mvc.perform(get("/greeting?name=sv"))

        // then
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(expectedId))
        .andExpect(jsonPath("$.content").value(expectedContent));
    System.out.println("********************************INTEGRATION****************");
  }

  @Test
  @Order(2)
  public void shouildReturnDefault() throws Exception {

    // given
    String expectedContent = "Hello, World!";
    String res = mvc.perform(get("/greeting")).andReturn().getResponse().getContentAsString();
    Greeting response = mapper.readValue(res, Greeting.class);
    long expectedId ;
    if (response != null) {
      expectedId = response.getId() + 1L;
    } else {
      expectedId = 0L;
    }

    // when
    mvc.perform(get("/greeting"))

        // then
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(expectedId))
        .andExpect(jsonPath("$.content").value(expectedContent));
  }
}