package client.Main.model;

import java.util.ArrayList;
import java.util.List;

public class RootGoalData {
  private List<Goal> goal = new ArrayList<>();

  // Getter & Setter
  public List<Goal> getGoal() {
    return goal;
  }

  public void setGoal(List<Goal> goal) {
    this.goal = goal;
  }

  @Override
  public String toString() {
    return "RootGoalData{" +
        "goal=" + goal +
        '}';
  }
}
