package client;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
  public ProfilePanel() {
    setLayout(new BorderLayout());
    JLabel label = new JLabel("Profile Screen", SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 20));
    add(label, BorderLayout.CENTER);
  }

}
