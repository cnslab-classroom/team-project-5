package client.Main;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ListPanel extends JPanel {

  public ListPanel() {
    setLayout(new BorderLayout());
    JLabel label = new JLabel("List Screen", SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 20));
    add(label, BorderLayout.CENTER);
  }
}
