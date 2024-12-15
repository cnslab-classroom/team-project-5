package client.Main.model;

public class MemberItem {
  private String emoji;
  private String name;

  public MemberItem(String emoji, String name) {
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
