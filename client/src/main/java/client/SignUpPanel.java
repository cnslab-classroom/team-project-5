
package client;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


// SignUp 클래스
class SignUpPanel extends JPanel {
    public SignUpPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Sign Up Page", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, BorderLayout.CENTER);
    }
}