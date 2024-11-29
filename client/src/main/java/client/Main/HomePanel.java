package client.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class HomePanel extends JPanel {
    public HomePanel() {
        setLayout(new BorderLayout()); // BorderLayout 사용

        // 메인 텍스트와 명언을 담는 패널
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // 수직 정렬
        centerPanel.setBackground(Color.WHITE);

        // 메인 텍스트 (왼쪽 정렬)
        JLabel HomeLabel = new JLabel("<html>오늘도 빛나는 하루입니다, <span style='color:blue;'>버밀리언님!<br>무엇을 먼저 시작해볼까요?</html>");
        HomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        HomeLabel.setAlignmentX(LEFT_ALIGNMENT); // 왼쪽 정렬

        // 명언 텍스트 (왼쪽 정렬)
        JLabel HomeQuote = new JLabel("<html>“Shoot for the moon. Even if you miss, you'll land among the stars.”<br>— Norman Vincent Peale</html>");
        HomeQuote.setFont(new Font("Arial", Font.ITALIC, 11));
        HomeQuote.setBorder(new LineBorder(Color.GRAY, 2, true)); // 테두리 추가
        HomeQuote.setBackground(new Color(240, 240, 240)); // 배경색 설정
        HomeQuote.setOpaque(true); // 배경색 활성화
        HomeQuote.setAlignmentX(LEFT_ALIGNMENT); // 왼쪽 정렬
        HomeQuote.setMaximumSize(new Dimension(400, 100)); // 최대 크기 제한

        // 메인 텍스트와 명언 추가
        centerPanel.add(Box.createRigidArea(new Dimension(5, 20))); // 상단 여백
        centerPanel.add(HomeLabel); // 메인 텍스트 추가
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // 간격 추가
        centerPanel.add(HomeQuote); // 명언 텍스트 추가

        // 오른쪽 패널 구성
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS)); // 수직 정렬
        eastPanel.setBackground(Color.WHITE); // 흰색 배경

        // 목표 섹션
        JLabel goalTitle = new JLabel("<html><b>계획한 대로 차근차근,<br>오늘의 <span style='color:green;'>목표들</span>을 확인해보세요! ✅</b></html>");
        goalTitle.setFont(new Font("Arial", Font.BOLD, 20));
        goalTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel goalBox = new JPanel();
        goalBox.setLayout(new BoxLayout(goalBox, BoxLayout.Y_AXIS));
        goalBox.setBorder(new LineBorder(Color.GRAY, 2, true));
        goalBox.setBackground(new Color(240, 240, 240));
        goalBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        JCheckBox goal1 = new JCheckBox("1일 1백준 🖤");
        JCheckBox goal2 = new JCheckBox("신나는 방 청소 ✏️");
        JCheckBox goal3 = new JCheckBox("기초영작문 노트정리 📝");
        goal1.setBackground(new Color(240, 240, 240));
        goal2.setBackground(new Color(240, 240, 240));
        goal3.setBackground(new Color(240, 240, 240));

        goalBox.add(goal1);
        goalBox.add(goal2);
        goalBox.add(goal3);

        // 일정 섹션
        JLabel scheduleTitle = new JLabel("<html><b>다가오는 <span style='color:blue;'>일정들</span>을 확인해보세요.<br>중요한 시간을 놓치지 마세요! ⏰</b></html>");
        scheduleTitle.setFont(new Font("Arial", Font.BOLD, 20));
        scheduleTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel scheduleBox = new JPanel();
        scheduleBox.setLayout(new BoxLayout(scheduleBox, BoxLayout.Y_AXIS));
        scheduleBox.setBorder(new LineBorder(Color.GRAY, 2, true));
        scheduleBox.setBackground(new Color(240, 240, 240));
        scheduleBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel schedule1 = new JLabel("🕒 12:00 계획서 작성 팀 프로젝트 회의 🤝");
        JLabel schedule2 = new JLabel("🕓 16:30 자료구조 튜터링 😎");
        JLabel schedule3 = new JLabel("🕕 18:30 친구들과 저녁 약속 🔥");

        scheduleBox.add(schedule1);
        scheduleBox.add(schedule2);
        scheduleBox.add(schedule3);

        // 학습 목표 섹션
        JLabel studyTitle = new JLabel("<html><b>시험기간도 하루비와 함께라면 오케이!<br>친구들과 함께 <span style='color:red;'>학습 목표</span>를 설정해봐요! 💯</b></html>");
        studyTitle.setFont(new Font("Arial", Font.BOLD, 20));
        studyTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel studyBox = new JPanel();
        studyBox.setLayout(new BoxLayout(studyBox, BoxLayout.Y_AXIS));
        studyBox.setBorder(new LineBorder(Color.GRAY, 2, true));
        studyBox.setBackground(new Color(240, 240, 240));
        studyBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel study1 = new JLabel("📖 ~2024.10.08. 시스템 소프트웨어 p.52");
        JLabel study2 = new JLabel("📖 ~2024.10.14. 선형대수학 Ch.03 Vector");
        JLabel study3 = new JLabel("📖 ~2024.10.21. 자료구조실습 개인 프로젝트");

        studyBox.add(study1);
        studyBox.add(study2);
        studyBox.add(study3);

        // 각 섹션의 타이틀과 박스를 오른쪽 패널에 추가
        eastPanel.add(Box.createRigidArea(new Dimension(0, 20))); // 간격
        eastPanel.add(goalTitle);
        eastPanel.add(goalBox);
        eastPanel.add(Box.createRigidArea(new Dimension(0, 20))); // 간격
        eastPanel.add(scheduleTitle);
        eastPanel.add(scheduleBox);
        eastPanel.add(Box.createRigidArea(new Dimension(0, 20))); // 간격
        eastPanel.add(studyTitle);
        eastPanel.add(studyBox);

        // 중앙과 오른쪽 패널 추가
        add(centerPanel, BorderLayout.CENTER); // 중앙 패널
        add(eastPanel, BorderLayout.EAST); // 오른쪽 패널
    }
}
