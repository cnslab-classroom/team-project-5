package client.Main.model;

public class Saying {
  private String text;
  private String speaker;

  public Saying(String text, String speaker) {
    this.text = text;
    this.speaker = speaker;
  }

  public String getText() {
    return text;
  }

  public String getSpeaker() {
    return speaker;
  }
}