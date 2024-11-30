package client.SignUp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import client.Frame;
import client.Main.ClientMain;

public class SuccessSignUpPanel extends JPanel {
    private final JLabel successLabel;
    private final JLabel subLabel;
    private final String successFullText = "가입이 완료되었습니다.";
    private final String subFullText = "목표 달성을 위한 여정을 지금 시작하세요.";
    private final StringBuilder currentSuccessText = new StringBuilder();
    private final StringBuilder currentSubText = new StringBuilder();
    private Timer animationTimer;
    private Timer switchTimer;
    private final Frame parentFrame;

    public SuccessSignUpPanel(Frame parentFrame) {
        this.parentFrame = parentFrame;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        successLabel = new JLabel("");
        successLabel.setFont(new Font("Paperlogy", Font.BOLD, 24));
        successLabel.setAlignmentX(CENTER_ALIGNMENT);

        subLabel = new JLabel("");
        subLabel.setFont(new Font("Paperlogy", Font.PLAIN, 16));
        subLabel.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createRigidArea(new Dimension(0, 100)));
        add(successLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(subLabel);

        startTextAnimation();
    }

    private void startTextAnimation() {
        animationTimer = new Timer(100, e -> {
            if (currentSuccessText.length() < successFullText.length()) {
                currentSuccessText.append(successFullText.charAt(currentSuccessText.length()));
                successLabel.setText(currentSuccessText.toString());
            } else if (currentSubText.length() < subFullText.length()) {
                currentSubText.append(subFullText.charAt(currentSubText.length()));
                subLabel.setText(currentSubText.toString());
            } else {
                animationTimer.stop();
                startSwitchTimer();
            }
        });

        animationTimer.start();
    }

    private void startSwitchTimer() {
        switchTimer = new Timer(5000, e -> {
            parentFrame.switchToPanel(new ClientMain());
            switchTimer.stop();
        });

        switchTimer.setRepeats(false);
        switchTimer.start();
    }
}
