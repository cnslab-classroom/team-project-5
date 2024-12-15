package client.Main.model;

public class Plan {
  private String text;
  private String date;

  public Plan(String text, String date) {
    this.text = text;
    this.date = date;
  }

  public String getText() {
    return text;
  }

  public String getDate() {
    return date;
  }
}