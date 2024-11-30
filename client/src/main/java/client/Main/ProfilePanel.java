package client.Main;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ProfilePanel extends JPanel {
  public ProfilePanel() {
    setLayout(new BorderLayout());
    JLabel label = new JLabel("Profile Screen", SwingConstants.CENTER);
    label.setFont(new Font("Paperlogy", Font.BOLD, 20));
    add(label, BorderLayout.CENTER);
  }

}
