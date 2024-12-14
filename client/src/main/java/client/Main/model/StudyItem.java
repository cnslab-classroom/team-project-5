package client.Main.model;

public class StudyItem {
  private String emoji;
  private String name;

  public StudyItem(String emoji, String name) {
    this.emoji = emoji;
    this.name = name;
  }

  public String getEmoji() {
    return emoji;
  }

  public String getName() {
    return name;
  }
}