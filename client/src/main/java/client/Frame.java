package client;

// 외부 import
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.SignIn.SignInPanel;
import client.SignUp.SignUpPanel;

public class Frame extends JFrame {
    private JPanel panel; // 현재 패널 참조

    public Frame() {
        setTitle("Harubi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800); // 창 크기 설정
        setLayout(new BorderLayout()); // BorderLayout 사용

        // 초기 패널 설정
        panel = createWelcomePanel(this);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    // 첫 화면 패널 생성
    private JPanel createWelcomePanel(Frame parentFrame) {

        // 부모 컨테이너 (returnPanel을 중앙에 배치)
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setBackground(Color.WHITE);

        JPanel returnPanel = new JPanel();
        returnPanel.setLayout(new BoxLayout(returnPanel, BoxLayout.Y_AXIS)); // 수직 정렬
        returnPanel.setBackground(Color.WHITE); // 배경색 설정
        returnPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 하루비
        JLabel titleLabel = new JLabel("하루비");
        titleLabel.setFont(new Font("Paperlogy", Font.BOLD, 120)); // 폰트 크기 및 스타일 설정
        titleLabel.setForeground(new Color(0, 102, 255)); // 파란색 텍스트
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 당신의 기록을 이어가세요.
        JLabel subtitleLabel = new JLabel("당신의 기록을 이어가세요.");
        subtitleLabel.setFont(new Font("Paperlogy", Font.BOLD, 20));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 이모지
        JLabel emojiLabel = new JLabel("📝🖋✏📖📆🏃");
        emojiLabel.setFont(new Font("Paperlogy", Font.PLAIN, 30)); // 크기 설정
        emojiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 회원가입 버튼
        RoundedButton signUpButton = new RoundedButton("회원가입", new Color(240, 240, 240));
        signUpButton.setFont(new Font("Paperlogy", Font.BOLD, 20));
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.setMaximumSize(new Dimension(200, 50)); // 버튼 크기 제한
        signUpButton.setForeground(new Color(0, 0, 0)); // 버튼 텍스트 색상

        // 로그인 버튼
        RoundedButton signInButton = new RoundedButton("로그인", new Color(240, 240, 240));
        signInButton.setFont(new Font("Paperlogy", Font.BOLD, 20));
        signInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signInButton.setMaximumSize(new Dimension(200, 50)); // 버튼 크기 제한
        signInButton.setForeground(new Color(0, 0, 0)); // 버튼 텍스트 색상

        // 버튼 동작 설정
        signUpButton.addActionListener(e -> switchToPanel(new SignUpPanel(parentFrame)));
        signInButton.addActionListener(e -> switchToPanel(new SignInPanel(parentFrame)));

        // 컴포넌트 추가
        returnPanel.add(titleLabel);                              // 타이틀
        returnPanel.add(subtitleLabel);                           // 부제목
        returnPanel.add(emojiLabel);                              // 이모지
        returnPanel.add(signUpButton);                            // 회원가입 버튼
        returnPanel.add(signInButton);                            // 로그인 버튼

        // 가운데 정렬을 위한 Glue 추가
        mainContainer.add(Box.createVerticalGlue());
        mainContainer.add(returnPanel);
        mainContainer.add(Box.createVerticalGlue());

        return mainContainer;
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