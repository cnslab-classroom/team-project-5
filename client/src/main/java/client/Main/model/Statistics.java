package client.Main.model;

public class Statistics {
  private String date;
  private int achievement;

  public Statistics(String date, int achievement) {
    this.date = date;
    this.achievement = achievement;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getAchievement() {
    return achievement;
  }

  public void setAchievement(int achievement) {
    this.achievement = achievement;
  }
}