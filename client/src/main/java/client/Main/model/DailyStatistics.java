package client.Main.model;

public class DailyStatistics {
  private String goalText;
  private double goalDateAchievement;
  private int goalWholeDate;
  private double goalPercent;

  public DailyStatistics(String goalText, double goalDateAchievement, int goalWholeDate, double goalPercent) {
    this.goalText = goalText;
    this.goalDateAchievement = goalDateAchievement;
    this.goalWholeDate = goalWholeDate;
    this.goalPercent = goalPercent;
  }

  public String getGoalText() {
    return goalText;
  }

  public void setGoalText(String goalText) {
    this.goalText = goalText;
  }

  public double getGoalDateAchievement() {
    return goalDateAchievement;
  }

  public void setGoalDateAchievement(double goalDateAchievement) {
    this.goalDateAchievement = goalDateAchievement;
  }

  public int getGoalWholeDate() {
    return goalWholeDate;
  }

  public void setGoalWholeDate(int goalWholeDate) {
    this.goalWholeDate = goalWholeDate;
  }

  public double getGoalPercent() {
    return goalPercent;
  }

  public void setGoalPercent(double goalPercent) {
    this.goalPercent = goalPercent;
  }
}