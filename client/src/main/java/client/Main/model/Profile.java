package client.Main.model;

public class Profile {
  private String name;
  private int status;
  private String icon;
  private String bio;
  private String affiliation;

  public Profile(String name, int status, String icon, String bio, String affiliation) {
    this.name = name;
    this.status = status;
    this.icon = icon;
    this.bio = bio;
    this.affiliation = affiliation;
  }

  public String getName() {
    return name;
  }

  public int getStatus() {
    return status;
  }

  public String getIcon() {
    return icon;
  }

  public String getBio() {
    return bio;
  }

  public String getAffiliation() {
    return affiliation;
  }
}