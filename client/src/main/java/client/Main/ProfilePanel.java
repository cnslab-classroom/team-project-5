package client.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ProfilePanel extends JPanel {
  public ProfilePanel() {
    setLayout(new BorderLayout(10, 10));
    setBackground(Color.WHITE);
    setBorder(new EmptyBorder(20, 20, 20, 20)); // 여백 설정

    // 타이틀 "프로필"
    JLabel titleLabel = new JLabel("프로필");
    titleLabel.setFont(new Font("Paperlogy", Font.BOLD, 16));
    add(titleLabel, BorderLayout.NORTH);

    // 메인 패널
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBackground(new Color(240, 240, 240));
    mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

    // 1. 스트릭 타이틀 패널
    JPanel streakTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)); // 왼쪽 정렬
    streakTitlePanel.setBackground(new Color(240, 240, 240));
    streakTitlePanel.setBorder(new EmptyBorder(10, 0, 10, 0));
    JLabel strick = new JLabel("스트릭");
    strick.setFont(new Font("Paperlogy", Font.BOLD, 16));
    streakTitlePanel.add(strick);
    mainPanel.add(streakTitlePanel);

    // 2. 스트릭 패널
    JPanel streakPanel = createStreakPanel();
    mainPanel.add(streakPanel);

    // 3. 이름 패널
    JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)); // 왼쪽 정렬
    namePanel.setBackground(new Color(240, 240, 240));

    JLabel name = new JLabel("김수오 🥰");
    name.setBorder(new EmptyBorder(10, 0, 10, 0));
    name.setFont(new Font("Paperlogy", Font.BOLD, 16));
    namePanel.add(name);

    JButton editButton = new JButton("프로필 수정");
    editButton.setFont(new Font("Paperlogy", Font.PLAIN, 12));
    editButton.setBackground(Color.white); // 초록색
    namePanel.add(editButton);

    mainPanel.add(namePanel);

    // 4. 유저 정보 패널
    JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    userInfoPanel.add(createUserInfoPanel());
    mainPanel.add(userInfoPanel);

    add(mainPanel, BorderLayout.CENTER);
  }

  // 스트릭 패널 생성
  private JPanel createStreakPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setPreferredSize(new Dimension(300, 100));
    panel.setBackground(new Color(34, 139, 34)); // 초록색
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));

    JLabel streakText = new JLabel("👍", SwingConstants.LEFT);
    streakText.setFont(new Font("Paperlogy", Font.BOLD, 20));
    streakText.setForeground(Color.WHITE);

    JLabel streakDays = new JLabel("219일", SwingConstants.CENTER);
    streakDays.setFont(new Font("Paperlogy", Font.BOLD, 50));
    streakDays.setForeground(Color.WHITE);

    JLabel streakMessage = new JLabel("오늘 목표를 완료했어요!", SwingConstants.CENTER);
    streakMessage.setFont(new Font("Paperlogy", Font.PLAIN, 14));
    streakMessage.setForeground(Color.WHITE);

    panel.add(streakText, BorderLayout.NORTH);
    panel.add(streakDays, BorderLayout.CENTER);
    panel.add(streakMessage, BorderLayout.SOUTH);

    return panel;
  }

  // 유저 정보 패널 생성
  private JPanel createUserInfoPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(new LineBorder(Color.GRAY, 1, true));
    panel.setBackground(Color.WHITE);
    panel.setPreferredSize(new Dimension(300, 150));

    JLabel introLabel = new JLabel("<html><b>print(\"Hello, World!\")</b></html>");
    introLabel.setFont(new Font("paperlogy", Font.BOLD, 16));
    introLabel.setBorder(new EmptyBorder(5, 10, 5, 10));

    JLabel nameLabel = new JLabel("이름: 김수오");
    nameLabel.setFont(new Font("paperlogy", Font.PLAIN, 14));
    nameLabel.setBorder(new EmptyBorder(5, 10, 0, 10));

    JLabel statusLabel = new JLabel("상태: 🟢");
    statusLabel.setFont(new Font("paperlogy", Font.PLAIN, 14));
    statusLabel.setBorder(new EmptyBorder(5, 10, 0, 10));

    JLabel emojiLabel = new JLabel("이모지: 🥰");
    emojiLabel.setFont(new Font("paperlogy", Font.PLAIN, 14));
    emojiLabel.setBorder(new EmptyBorder(5, 10, 0, 10));

    JLabel introOneLineLabel = new JLabel("한 줄 소개: print(\"Hello, World!\")");
    introOneLineLabel.setFont(new Font("paperlogy", Font.PLAIN, 14));
    introOneLineLabel.setBorder(new EmptyBorder(5, 10, 0, 10));

    JLabel affiliationLabel = new JLabel("소속: Kwangwoon Univ.");
    affiliationLabel.setFont(new Font("paperlogy", Font.PLAIN, 14));
    affiliationLabel.setBorder(new EmptyBorder(5, 10, 10, 10));

    // 패널에 각 항목 추가
    panel.add(introLabel);
    panel.add(nameLabel);
    panel.add(statusLabel);
    panel.add(emojiLabel);
    panel.add(introOneLineLabel);
    panel.add(affiliationLabel);

    // panel.add(userStatus, BorderLayout.CENTER);

    return panel;
  }
}