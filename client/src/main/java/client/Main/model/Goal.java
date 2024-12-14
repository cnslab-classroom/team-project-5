package client.Main.model;

public class Goal {
  private String text;
  private boolean status;

  public Goal(String text, boolean status) {
    this.text = text;
    this.status = status;
  }

  public String getText() {
    return text;
  }

  public boolean getStatus() {
    return status;
  }
}