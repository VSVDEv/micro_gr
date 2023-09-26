package vsvdev.co.ua.micro.entities;

public class Greeting {

  private long id;
  private String content;

  public Greeting(long id, String content) {
    this.id = id;
    this.content = content;
  }

  public Greeting() {
    // empty
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }
}
