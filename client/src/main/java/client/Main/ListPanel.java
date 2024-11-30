package client.Main;

import javax.swing.*;
import java.awt.*;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;

public class ListPanel extends JPanel {
  private Border outerBorder = new LineBorder(Color.GRAY, 2, true);
  private Border innerBorder = new EmptyBorder(0, 10, 0, 10);

  public ListPanel() {
    setLayout(new BorderLayout(10, 10)); // 패널 간 간격
    setBorder(new EmptyBorder(30, 30, 30, 30)); // 전체 여백 설정
    setBackground(Color.WHITE);

    // 1. 상단 영역 (텍스트와 그래프)
    JPanel topPanel = studyListPanel();
    add(topPanel, BorderLayout.NORTH);

    // 2. 중앙 영역 (통계 정보)
    JPanel middlePanel = studyPanel();
    add(middlePanel, BorderLayout.CENTER);

  }

  private JPanel studyListPanel() {
    JPanel studyListPanel = new JPanel(new BorderLayout());
    studyListPanel.setBackground(Color.WHITE);

    JPanel titlePanel = new JPanel(new BorderLayout());
    titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
    titlePanel.setBackground(Color.WHITE);
    titlePanel.setBorder(new EmptyBorder(0, 10, 10, 10)); // 내부 여백

    JLabel titleLabel = new JLabel("스터디 목록");
    titleLabel.setFont(new Font("paperlogy", Font.PLAIN, 20));
    titleLabel.setHorizontalAlignment(SwingConstants.LEFT); // 왼쪽 정렬
    titlePanel.add(titleLabel, BorderLayout.WEST);

    JButton createButton = new JButton("+");
    createButton.setFont(new Font("paperlogy", Font.PLAIN, 20));
    createButton.setHorizontalAlignment(SwingConstants.CENTER); // 오른쪽 정렬
    titlePanel.add(createButton, BorderLayout.EAST);

    studyListPanel.add(titlePanel, BorderLayout.NORTH);

    JPanel ListPanel = new JPanel();
    ListPanel.setLayout(new BoxLayout(ListPanel, BoxLayout.Y_AXIS));
    ListPanel.setBackground(new Color(240, 240, 240));
    ListPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

    String[] emoge = { "🍀", "💡", "📚" };
    String[] name = { "빡공스터디", "객체지향프로그래밍 팀 프로젝트", "독서토론" };
    boolean[] favorite = { true, true, false };

    for (int i = 0; i < 3; i++) {
      JPanel studyPanel = new JPanel(new BorderLayout());

      JLabel imogeLabel = new JLabel(emoge[i] + " ");
      imogeLabel.setFont(new Font("paperlogy", Font.PLAIN, 20));
      imogeLabel.setHorizontalAlignment(SwingConstants.LEFT); // 왼쪽 정렬
      studyPanel.add(imogeLabel, BorderLayout.WEST);

      JLabel nameLabel = new JLabel(name[i]);
      nameLabel.setFont(new Font("paperlogy", Font.PLAIN, 20));
      nameLabel.setHorizontalAlignment(SwingConstants.LEFT); // 왼쪽 정렬
      studyPanel.add(nameLabel, BorderLayout.CENTER);

      if (favorite[i]) {
        JButton favoriteButton = new JButton("★");
        favoriteButton.setFont(new Font("paperlogy", Font.PLAIN, 20));
        favoriteButton.setHorizontalAlignment(SwingConstants.CENTER); // 오른쪽 정렬
        studyPanel.add(favoriteButton, BorderLayout.EAST);
      } else {
        JButton favoriteButton = new JButton("☆");
        favoriteButton.setFont(new Font("paperlogy", Font.PLAIN, 20));
        favoriteButton.setHorizontalAlignment(SwingConstants.CENTER); // 오른쪽 정렬
        studyPanel.add(favoriteButton, BorderLayout.EAST);
      }

      ListPanel.add(studyPanel);
      studyListPanel.add(ListPanel, BorderLayout.CENTER);

    }

    return studyListPanel;
  }

  private JPanel studyPanel() {
    JPanel studyPanel = new JPanel(new BorderLayout());
    studyPanel.setBorder(new EmptyBorder(20, 0, 10, 0)); // 내부 여백
    studyPanel.setBackground(Color.WHITE);

    // 콤보박스
    String[] names = { "빡공스터디", "객체지향프로그래밍 팀 프로젝트", "독서토론" };
    JComboBox<String> comboBox = new JComboBox<>(names);
    comboBox.setFont(new Font("Arial", Font.PLAIN, 20));

    // 콤보박스 선택 이벤트
    comboBox.addActionListener(e -> {
      String selectedName = (String) comboBox.getSelectedItem();
    });

    // 상단 패널을 메인 패널에 추가
    studyPanel.add(comboBox, BorderLayout.NORTH);

    // 중앙 패널 (텍스트 영역)
    JPanel textPanel = new JPanel();
    textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
    textPanel.setBackground(new Color(240, 240, 240));
    textPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

    // 텍스트 영역 - 목표
    JPanel goalPanel = new JPanel(new BorderLayout());
    goalPanel.setBackground(new Color(240, 240, 240));

    JPanel goalLabelPanel = new JPanel();
    goalLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    goalLabelPanel.setBackground(new Color(240, 240, 240));

    JLabel goalLabel = new JLabel("목표🎯");
    goalLabel.setForeground(Color.RED);
    goalLabel.setFont(new Font("paperlogy", Font.BOLD, 16));
    goalLabelPanel.add(goalLabel, BorderLayout.WEST);
    JButton goalButton = new JButton("+");
    goalButton.setFont(new Font("paperlogy", Font.PLAIN, 16));
    goalLabelPanel.add(goalButton, BorderLayout.EAST);

    goalPanel.add(goalLabelPanel, BorderLayout.NORTH);

    JPanel goalListPanel = new JPanel();
    goalListPanel.setLayout(new BoxLayout(goalListPanel, BoxLayout.Y_AXIS));
    goalListPanel.setBackground(Color.WHITE);
    goalListPanel.setBorder(outerBorder);

    String[] goalNames = { "1일 1백준", "신나는 방 청소", "기초영작문 노트정리" };
    String[] goalPeriod = { "2024.10.08", "2024.10.14", "2024.10.21" };

    for (int i = 0; i < 3; i++) {
      JPanel goalPanelList = new JPanel(new BorderLayout());
      goalPanelList.setBackground(Color.WHITE);

      JLabel goalPeriodLabel = new JLabel("✏️ " + goalPeriod[i] + " ");
      goalPeriodLabel.setFont(new Font("paperlogy", Font.PLAIN, 16));
      goalPeriodLabel.setHorizontalAlignment(SwingConstants.LEFT); // 왼쪽 정렬
      goalPanelList.add(goalPeriodLabel, BorderLayout.WEST);

      JLabel goalName = new JLabel(goalNames[i]);
      goalName.setFont(new Font("paperlogy", Font.PLAIN, 16));
      goalName.setHorizontalAlignment(SwingConstants.LEFT); // 왼쪽 정렬
      goalPanelList.add(goalName, BorderLayout.CENTER);

      goalListPanel.add(goalPanelList);
    }

    goalPanel.add(goalListPanel, BorderLayout.CENTER);

    textPanel.add(goalPanel);

    // 중앙 텍스트 패널을 메인 패널에 추가
    studyPanel.add(textPanel, BorderLayout.CENTER);

    return studyPanel;
  }

}
