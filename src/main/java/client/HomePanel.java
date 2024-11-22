package client;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
  public HomePanel() {
    setLayout(new BorderLayout());
    JLabel label = new JLabel("Home Screen", SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 20));
    add(label, BorderLayout.CENTER);
  }
}
