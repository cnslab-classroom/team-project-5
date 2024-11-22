package client;

import javax.swing.*;
import java.awt.*;

public class StatisticsPanel extends JPanel {
  public StatisticsPanel() {
    setLayout(new BorderLayout());
    JLabel label = new JLabel("Statistics Screen", SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 20));
    add(label, BorderLayout.CENTER);
  }

}
