
package client;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


// SignIn 클래스
class SignInPanel extends JPanel {
    public SignInPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Sign In Page", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, BorderLayout.CENTER);
    }
}