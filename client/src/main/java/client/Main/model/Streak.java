package client.Main.model;

public class Streak {
  private int streakDays;
  private boolean todayGoalCompleted;

  public Streak(int streakDays, boolean todayGoalCompleted) {
    this.streakDays = streakDays;
    this.todayGoalCompleted = todayGoalCompleted;
  }

  public int getStreakDays() {
    return streakDays;
  }

  public boolean isTodayGoalCompleted() {
    return todayGoalCompleted;
  }
}