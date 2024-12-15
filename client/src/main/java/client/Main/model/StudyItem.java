package client.Main.model;

public class StudyItem {
  private String date;
  private String text;

  public StudyItem(String date, String text) {
    this.date = date;
    this.text = text;
  }

  public String getDate() {
    return date;
  }

  public String getText() {
    return text;
  }
}
