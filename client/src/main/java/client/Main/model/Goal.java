package client.Main.model;

import java.util.List;

public class Goal {
  private String goalText;
  private Boolean goalStatus;
  private Long goalId;

  public Goal(String goalText, Boolean goalStatus, Long goalId) {
    this.goalText = goalText;
    this.goalStatus = goalStatus;
    this.goalId = goalId;
  }

  public String getText() {
    return goalText;
  }

  public Boolean getStatus() {
    return goalStatus;
  }

  public Long getId() {
    return goalId;
  }

  @Override
  public String toString() {
    return "Goal{" +
        "goalText='" + goalText + '\'' +
        ", goalStatus=" + goalStatus +
        ", goalId=" + goalId +
        '}';
  }
}