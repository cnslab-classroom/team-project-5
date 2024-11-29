package client;

// 외부 import
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 내부 import
import client.SignIn.SignInPanel;
import client.SignUp.SignUpPanel;

public class Frame extends JFrame {
    private JPanel panel; // 현재 패널 참조

    public Frame() {
        setTitle("Harubi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000); // 창 크기 설정
        setLayout(new BorderLayout()); // BorderLayout 사용

        // 초기 패널 설정
        panel = createWelcomePanel(this);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    // 첫 화면 패널 생성
    private JPanel createWelcomePanel(Frame parentFrame) {
        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.Y_AXIS)); // 수직 정렬

        // 하루비
        JLabel titleLabel = new JLabel("Harubi");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 90)); // 폰트 크기 및 스타일 설정
        titleLabel.setForeground(new Color(0, 102, 255)); // 파란색 텍스트
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 당신의 기록을 이어가세요.
        JLabel subtitleLabel = new JLabel("Continue Your Story.");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 이모지
        JLabel emojiLabel = new JLabel("(emojis)");
        emojiLabel.setFont(new Font("Arial", Font.PLAIN, 30)); // 크기 설정
        emojiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 회원가입
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 20));
        signUpButton.setBackground(new Color(240, 240, 240)); // 버튼 배경색
        signUpButton.setFocusPainted(false); // 버튼 선택 효과 제거
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.setMaximumSize(new Dimension(200, 50)); // 버튼 크기 제한

        // 로그인
        JButton signInButton = new JButton("Sign In");
        signInButton.setFont(new Font("Arial", Font.BOLD, 20));
        signInButton.setBackground(new Color(240, 240, 240)); // 버튼 배경색
        signInButton.setFocusPainted(false); // 버튼 선택 효과 제거
        signInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signInButton.setMaximumSize(new Dimension(200, 50)); // 버튼 크기 제한

        // signUpButton 클릭 이벤트
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(new SignUpPanel(parentFrame));
            } // SignUpPanel로 전환
        });

        // signInButton 클릭 이벤트
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(new SignInPanel(parentFrame));
            } // SignInPanel로 전환
        });

        // 여백 추가
        returnPanel.add(Box.createRigidArea(new Dimension(0, 50))); // (상단 여백)
        returnPanel.add(titleLabel);                                             // 하루비
        returnPanel.add(Box.createRigidArea(new Dimension(0, 20))); // 
        returnPanel.add(subtitleLabel);                                          // 당신의 기록을 이어가세요.
        returnPanel.add(Box.createRigidArea(new Dimension(0, 30))); // 
        returnPanel.add(emojiLabel);                                             // 이모지
        returnPanel.add(Box.createRigidArea(new Dimension(0, 50))); // 
        returnPanel.add(signUpButton);                                           // 회원가입 버튼
        returnPanel.add(Box.createRigidArea(new Dimension(0, 20))); // 
        returnPanel.add(signInButton);                                           // 로그인 버튼

        return returnPanel;
    }

    // 패널 전환 메서드
    // !! extends JPanel을 하는 모든 Panel 클래스에서 사용 가능 !!
    public void switchToPanel(JPanel newPanel) {
        remove(panel);                             // 기존 패널 제거
        panel = newPanel;                          // 새로운 패널로 교체
        add(panel, BorderLayout.CENTER);           // 새 패널 추가
        revalidate();                              // 레이아웃 갱신
        repaint();                                 // 화면 갱신
    }

    // 메인 (시작)
    public static void main(String[] args) {
        new Frame();
    }
}