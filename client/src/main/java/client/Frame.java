package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    private JPanel currentPanel; // 현재 패널 참조

    public Frame() {
        setTitle("Harubi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000); // 창 크기 설정
        setLayout(new BorderLayout()); // BorderLayout 사용

        // 초기 패널 설정
        currentPanel = createFirstPanel();
        add(currentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // 첫 화면 패널 생성
    private JPanel createFirstPanel() {
        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.Y_AXIS)); // 수직 정렬
        firstPanel.setBackground(Color.WHITE);

        // 상단 텍스트
        JLabel titleLabel = new JLabel("Harubi");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 90)); // 폰트 크기 및 스타일 설정
        titleLabel.setForeground(new Color(0, 102, 255)); // 파란색 텍스트
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Continue Your Story.");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 이모지 섹션
        JLabel emojiLabel = new JLabel("(emojis)");
        emojiLabel.setFont(new Font("Arial", Font.PLAIN, 30)); // 크기 설정
        emojiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 버튼 섹션
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 20));
        signUpButton.setBackground(new Color(240, 240, 240)); // 버튼 배경색
        signUpButton.setFocusPainted(false); // 버튼 선택 효과 제거
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.setMaximumSize(new Dimension(200, 50)); // 버튼 크기 제한

        JButton signInButton = new JButton("Sign In");
        signInButton.setFont(new Font("Arial", Font.BOLD, 20));
        signInButton.setBackground(new Color(240, 240, 240)); // 버튼 배경색
        signInButton.setFocusPainted(false); // 버튼 선택 효과 제거
        signInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signInButton.setMaximumSize(new Dimension(200, 50)); // 버튼 크기 제한

        // 버튼 클릭 이벤트
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(new SignUpPanel());
            }
        });

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(new SignInPanel());
            }
        });

        // 여백 추가
        firstPanel.add(Box.createRigidArea(new Dimension(0, 50))); // 상단 여백
        firstPanel.add(titleLabel); // 상단 텍스트 추가
        firstPanel.add(Box.createRigidArea(new Dimension(0, 20))); // 간격
        firstPanel.add(subtitleLabel); // 서브 텍스트 추가
        firstPanel.add(Box.createRigidArea(new Dimension(0, 30))); // 간격
        firstPanel.add(emojiLabel); // 이모지 추가
        firstPanel.add(Box.createRigidArea(new Dimension(0, 50))); // 간격
        firstPanel.add(signUpButton); // 회원가입 버튼 추가
        firstPanel.add(Box.createRigidArea(new Dimension(0, 20))); // 간격
        firstPanel.add(signInButton); // 로그인 버튼 추가

        return firstPanel;
    }

    // 패널 전환 메서드
    private void switchToPanel(JPanel newPanel) {
        remove(currentPanel); // 기존 패널 제거
        currentPanel = newPanel; // 새로운 패널로 교체
        add(currentPanel, BorderLayout.CENTER); // 새 패널 추가
        revalidate(); // 레이아웃 갱신
        repaint(); // 화면 갱신
    }

    public static void main(String[] args) {
        new Frame();
    }
}