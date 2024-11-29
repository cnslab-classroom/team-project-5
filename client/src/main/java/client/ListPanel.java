package client;

import javax.swing.*;
import java.awt.*;

public class ListPanel extends JPanel {

  public ListPanel() {
    setLayout(new BorderLayout());
    JLabel label = new JLabel("List Screen", SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 20));
    add(label, BorderLayout.CENTER);
  }
}
