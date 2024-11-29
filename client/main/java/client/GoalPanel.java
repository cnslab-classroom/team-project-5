package client;

import javax.swing.*;
import java.awt.*;

public class GoalPanel extends JPanel {
  public GoalPanel() {
    setLayout(new BorderLayout());
    JLabel label = new JLabel("Goal Screen", SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 20));
    add(label, BorderLayout.CENTER);
  }
}
