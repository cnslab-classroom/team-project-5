package client.Main;

import javax.swing.*;
import java.awt.*;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

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
      imogeLabel.setFont(new Font("paperlogy", Font.PLAIN, 16));
      imogeLabel.setHorizontalAlignment(SwingConstants.LEFT); // 왼쪽 정렬
      studyPanel.add(imogeLabel, BorderLayout.WEST);

      JLabel nameLabel = new JLabel(name[i]);
      nameLabel.setFont(new Font("paperlogy", Font.PLAIN, 16));
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
    goalPanel.setBorder(new EmptyBorder(10, 0, 0, 10)); // 내부 여백

    JPanel goalLabelPanel = new JPanel();
    goalLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    goalLabelPanel.setBackground(new Color(240, 240, 240));

    JLabel goalLabel = new JLabel("목표 🎯");
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
    goalListPanel.setBorder(new EmptyBorder(5, 10, 5, 10)); // 내부 여백

    String[] goalNames = { "1일 1백준", "신나는 방 청소", "기초영작문 노트정리" };
    String[] goalPeriod = { "2024.10.08", "2024.10.14", "2024.10.21" };

    for (int i = 0; i < goalNames.length; i++) {
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

    // 텍스트 영역 - 멤버
    JPanel memberPanel = new JPanel(new BorderLayout());
    memberPanel.setBackground(new Color(240, 240, 240));
    memberPanel.setBorder(new EmptyBorder(10, 0, 10, 0)); // 내부 여백

    JPanel memberLabelPanel = new JPanel();
    memberLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    memberLabelPanel.setBackground(new Color(240, 240, 240));

    JLabel memberLabel = new JLabel("멤버 👥");
    memberLabel.setForeground(new Color(40, 167, 69));
    memberLabel.setFont(new Font("paperlogy", Font.BOLD, 16));
    memberLabelPanel.add(memberLabel, BorderLayout.WEST);
    JButton memberButton = new JButton("+");
    memberButton.setFont(new Font("paperlogy", Font.PLAIN, 16));
    memberLabelPanel.add(memberButton, BorderLayout.EAST);

    memberPanel.add(memberLabelPanel, BorderLayout.NORTH);

    JPanel memberListPanel = new JPanel();
    memberListPanel.setLayout(new BoxLayout(memberListPanel, BoxLayout.Y_AXIS));
    memberListPanel.setBackground(Color.WHITE);
    memberListPanel.setBorder(outerBorder);
    memberListPanel.setBorder(new EmptyBorder(5, 10, 5, 10)); // 내부 여백

    String[] memberEmoge = { "😀", "😄", "😆", "🥰" };
    String[] memberNames = { "김수오", "오은진", "최세연", "김단하" };

    for (int i = 0; i < memberNames.length; i++) {
      JPanel memberPanelList = new JPanel(new BorderLayout());
      memberPanelList.setBackground(Color.WHITE);

      JLabel memberEmogeLabel = new JLabel(memberEmoge[i] + " ");
      memberEmogeLabel.setFont(new Font("paperlogy", Font.PLAIN, 16));
      memberEmogeLabel.setHorizontalAlignment(SwingConstants.LEFT); // 왼쪽 정렬
      memberPanelList.add(memberEmogeLabel, BorderLayout.WEST);

      JLabel memberName = new JLabel(memberNames[i]);
      memberName.setFont(new Font("paperlogy", Font.PLAIN, 16));
      memberName.setHorizontalAlignment(SwingConstants.LEFT); // 왼쪽 정렬
      memberPanelList.add(memberName, BorderLayout.CENTER);

      memberListPanel.add(memberPanelList);
    }

    memberPanel.add(memberListPanel, BorderLayout.CENTER);

    // 텍스트 영역 - 레퍼런스
    JPanel referencePanel = new JPanel(new BorderLayout());
    referencePanel.setBackground(new Color(240, 240, 240));
    referencePanel.setBorder(new EmptyBorder(10, 0, 10, 0)); // 내부 여백

    JPanel referenceLabelPanel = new JPanel();
    referenceLabelPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    referenceLabelPanel.setBackground(new Color(240, 240, 240));

    JLabel referenceLabel = new JLabel("레퍼런스 🌐");
    referenceLabel.setForeground(Color.BLUE);
    referenceLabel.setFont(new Font("paperlogy", Font.BOLD, 16));
    referenceLabelPanel.add(referenceLabel, BorderLayout.WEST);
    JButton referenceButton = new JButton("+");
    referenceButton.setFont(new Font("paperlogy", Font.PLAIN, 16));
    referenceLabelPanel.add(referenceButton, BorderLayout.EAST);

    referencePanel.add(referenceLabelPanel, BorderLayout.NORTH);

    JPanel referenceListPanel = new JPanel();
    referenceListPanel.setLayout(new BoxLayout(referenceListPanel, BoxLayout.Y_AXIS));
    referenceListPanel.setBackground(Color.WHITE);
    referenceListPanel.setBorder(outerBorder);
    referenceListPanel.setBorder(new EmptyBorder(5, 10, 5, 10)); // 내부 여백

    String[] linkNames = { "Notiondsffsfsdsf", "github", "github", "github", "github" };
    String[] linkUrl = { "https://www.notion.com/ko", "https://github.com/cnslab-classroom/team-project-5",
        "https://github.com/cnslab-classroom/team-project-5", "https://github.com/cnslab-classroom/team-project-5",
        "https://github.com/cnslab-classroom/team-project-5" };

    for (int i = 0; i < linkNames.length; i++) {
      JPanel referencePanelList = new JPanel(new FlowLayout(FlowLayout.LEFT)); // FlowLayout 사용
      referencePanelList.setBackground(Color.WHITE);

      String htmlLink = String.format("<html><div style='width:300px;'>🔗 <a href=''>%s</a></div></html>",
          linkNames[i]);
      JLabel linkLabel = new JLabel(htmlLink);
      linkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
      linkLabel.setFont(new Font("paperlogy", Font.PLAIN, 16));

      // 마우스 클릭 이벤트로 링크 열기
      final String url = linkUrl[i];
      linkLabel.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          openWebpage(url); // URL 열기
        }
      });

      referencePanelList.add(linkLabel);
      referenceListPanel.add(referencePanelList);
    }

    referencePanel.add(referenceListPanel, BorderLayout.CENTER);

    textPanel.add(goalPanel);
    textPanel.add(memberPanel);
    textPanel.add(referencePanel);

    // 중앙 텍스트 패널을 메인 패널에 추가
    studyPanel.add(textPanel, BorderLayout.CENTER);

    return studyPanel;
  }

  // 링크 열기 메서드
  private void openWebpage(String url) {
    try {
      Desktop desktop = Desktop.getDesktop();
      desktop.browse(new URI(url));
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(this, "Failed to open the link: " + url);
    }
  }

}
