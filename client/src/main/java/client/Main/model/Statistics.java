package client.Main.model;

public class Statistics {
  private String date;
  private double achievement;

  public Statistics(String date, double achievement) {
    this.date = date;
    this.achievement = achievement;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public double getAchievement() {
    return achievement;
  }

  public void setAchievement(double achievement) {
    this.achievement = achievement;
  }
}