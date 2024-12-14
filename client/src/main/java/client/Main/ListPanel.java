package client.Main;

import javax.swing.*;
import java.awt.*;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import client.Main.fetchData.FetchStudyList;
import client.Main.fetchData.FetchStudyListAdd;
import client.Main.model.GroupDetail;
import client.Main.model.ListItem;
import client.Main.model.MemberItem;
import client.Main.model.ReferenceLinkItem;
import client.Main.model.StudyItem;

public class ListPanel extends JPanel {
  private Border outerBorder = new LineBorder(Color.GRAY, 2, true);
  private Border innerBorder = new EmptyBorder(0, 10, 0, 10);

  private List<ListItem> studyItems = new ArrayList<>();
  private int selectGroupId = 1;
  GroupDetail groupDetail = FetchStudyList.fetchGroupDetail(selectGroupId);

  public ListPanel() {
    setLayout(new BorderLayout(10, 10)); // 패널 간 간격
    setBorder(new EmptyBorder(30, 30, 30, 30)); // 전체 여백 설정
    setBackground(Color.WHITE);

    // 서버에서 데이터 가져오기
    fetchDataFromServer();

    // 1. 상단 영역 (텍스트와 그래프)
    JPanel topPanel = studyListPanel();
    add(topPanel, BorderLayout.NORTH);

    // 2. 중앙 영역 (통계 정보)
    JPanel middlePanel = studyPanel();
    add(middlePanel, BorderLayout.CENTER);

  }

  private void fetchDataFromServer() {
    // fetchStudyList 클래스에서 데이터 가져오기
    studyItems = FetchStudyList.fetchStudyListData();
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

    // 스터디 추가 Dialog
    createButton.addActionListener(e -> showStudyInputDialog());

    studyListPanel.add(titlePanel, BorderLayout.NORTH);

    JPanel listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    listPanel.setBackground(new Color(240, 240, 240));
    listPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, new EmptyBorder(10, 10, 0, 10)));

    // 서버에서 가져온 데이터를 기반으로 UI 생성
    for (ListItem item : studyItems) {
      JPanel studyPanel = new JPanel(new BorderLayout());
      studyPanel.setBackground(new Color(240, 240, 240));
      studyPanel.setBorder(new EmptyBorder(0, 0, 10, 0)); // 내부 여백

      JLabel emojiLabel = new JLabel(item.getEmoji());
      emojiLabel.setFont(new Font("paperlogy", Font.PLAIN, 16));
      emojiLabel.setBorder(new EmptyBorder(0, 0, 0, 5)); // 내부 여백
      studyPanel.add(emojiLabel, BorderLayout.WEST);

      JLabel nameLabel = new JLabel(item.getName());
      nameLabel.setFont(new Font("paperlogy", Font.PLAIN, 16));
      studyPanel.add(nameLabel, BorderLayout.CENTER);

      listPanel.add(studyPanel);
    }
    studyListPanel.add(listPanel, BorderLayout.CENTER);

    return studyListPanel;
  }

  private JPanel studyPanel() {
    JPanel studyPanel = new JPanel(new BorderLayout());
    studyPanel.setBorder(new EmptyBorder(20, 0, 10, 0)); // 내부 여백
    studyPanel.setBackground(Color.WHITE);

    // 콤보박스
    String[] names = new String[studyItems.size()];
    for (ListItem item : studyItems) {
      names[studyItems.indexOf(item)] = item.getName();
    }
    JComboBox<String> comboBox = new JComboBox<>(names);
    comboBox.setFont(new Font("paperlogy", Font.PLAIN, 20));

    // 콤보박스 선택 이벤트
    comboBox.addActionListener(e -> {
      String selectedName = (String) comboBox.getSelectedItem();
      for (ListItem item : studyItems) {
        if (item.getName().equals(selectedName)) {
          selectGroupId = studyItems.indexOf(item) + 1;
          System.out.println("Selected Group ID: " + selectGroupId);
        }
      }
      groupDetail = FetchStudyList.fetchGroupDetail(selectGroupId);
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

    goalButton.addActionListener(e -> showGoalInputDialog());

    goalPanel.add(goalLabelPanel, BorderLayout.NORTH);

    JPanel goalListPanel = new JPanel();
    goalListPanel.setLayout(new BoxLayout(goalListPanel, BoxLayout.Y_AXIS));
    goalListPanel.setBackground(Color.WHITE);
    goalListPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

    for (StudyItem study : groupDetail.studies) {
      JPanel goalPanelList = new JPanel(new BorderLayout());
      goalPanelList.setBackground(Color.WHITE);

      JLabel dateLabel = new JLabel("📅 " + study.getDate() + "  ");
      dateLabel.setFont(new Font("paperlogy", Font.PLAIN, 16));
      goalPanelList.add(dateLabel, BorderLayout.WEST);

      JLabel textLabel = new JLabel(study.getText());
      textLabel.setFont(new Font("paperlogy", Font.PLAIN, 16));
      goalPanelList.add(textLabel, BorderLayout.CENTER);

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

    memberButton.addActionListener(e -> showMemberInputDialog());

    memberPanel.add(memberLabelPanel, BorderLayout.NORTH);

    JPanel memberListPanel = new JPanel();
    memberListPanel.setLayout(new BoxLayout(memberListPanel, BoxLayout.Y_AXIS));
    memberListPanel.setBackground(Color.WHITE);
    memberListPanel.setBorder(outerBorder);
    memberListPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

    for (MemberItem member : groupDetail.members) {
      JPanel memberPanelList = new JPanel(new BorderLayout());
      memberPanelList.setBackground(Color.WHITE);

      JLabel emojiLabel = new JLabel(member.getEmoji() + " ");
      emojiLabel.setFont(new Font("paperlogy", Font.PLAIN, 16));
      memberPanelList.add(emojiLabel, BorderLayout.WEST);

      JLabel nameLabel = new JLabel(member.getName());
      nameLabel.setFont(new Font("paperlogy", Font.PLAIN, 16));
      memberPanelList.add(nameLabel, BorderLayout.CENTER);

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

    referenceButton.addActionListener(e -> showReferenceInputDialog());

    referencePanel.add(referenceLabelPanel, BorderLayout.NORTH);

    JPanel referenceListPanel = new JPanel();
    referenceListPanel.setLayout(new BoxLayout(referenceListPanel, BoxLayout.Y_AXIS));
    referenceListPanel.setBackground(Color.WHITE);
    referenceListPanel.setBorder(outerBorder);
    referenceListPanel.setBorder(new EmptyBorder(5, 10, 5, 10));

    for (ReferenceLinkItem reference : groupDetail.referenceLinks) {
      JPanel referencePanelList = new JPanel(new FlowLayout(FlowLayout.LEFT));
      referencePanelList.setBackground(Color.WHITE);

      String htmlLink = String.format("<html><div style='width:300px;'>🔗 <a href=''>%s</a></div></html>",
          reference.getName());

      JLabel linkLabel = new JLabel(htmlLink);
      linkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
      linkLabel.setFont(new Font("paperlogy", Font.PLAIN, 16));

      // 마우스 클릭 이벤트로 링크 열기
      final String url = reference.getUrl();
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

  private void refreshData() {
    // 서버에서 최신 데이터 가져오기
    studyItems = FetchStudyList.fetchStudyListData();
    groupDetail = FetchStudyList.fetchGroupDetail(selectGroupId);

    // 화면 갱신
    removeAll();
    JPanel topPanel = studyListPanel();
    JPanel middlePanel = studyPanel();

    add(topPanel, BorderLayout.NORTH);
    add(middlePanel, BorderLayout.CENTER);

    revalidate();
    repaint();
  }

  // 스터디 그룹 추가 Dialog
  private void showStudyInputDialog() {
    JTextField study_group_name = new JTextField();
    JTextField study_emoji = new JTextField();

    Object[] inputFields = {
        "이름:", study_group_name,
        "이모지:", study_emoji,
    };

    int option = JOptionPane.showConfirmDialog(
        this,
        inputFields,
        "스터디 그룹 추가",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE);

    if (option == JOptionPane.OK_OPTION) {
      String name = study_group_name.getText().trim();
      String emoji = study_emoji.getText().trim();

      if (name.isEmpty() || emoji.isEmpty()) {
        JOptionPane.showMessageDialog(this, "모든 필드를 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
      } else {
        // FetchStudyListGoal 클래스를 사용하여 서버로 POST 요청 전송
        FetchStudyListAdd.sendPostList(name, emoji);
        refreshData();
      }
    }
  }

  // 목표 추가 Dialog
  private void showGoalInputDialog() {
    JTextField nameField = new JTextField();
    JTextField endDateField = new JTextField();
    JTextField emojiField = new JTextField();

    Object[] inputFields = {
        "이름:", nameField,
        "종료일 (yyyy-MM-dd):", endDateField,
        "이모지:", emojiField
    };

    int option = JOptionPane.showConfirmDialog(
        this,
        inputFields,
        "목표 추가",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE);

    if (option == JOptionPane.OK_OPTION) {
      String name = nameField.getText().trim();
      String endDate = endDateField.getText().trim();
      String emoji = emojiField.getText().trim();

      if (name.isEmpty() || endDate.isEmpty() || emoji.isEmpty()) {
        JOptionPane.showMessageDialog(this, "모든 필드를 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
      } else {
        // FetchStudyListGoal 클래스를 사용하여 서버로 POST 요청 전송
        FetchStudyListAdd.sendPostGoal(name, endDate, emoji, selectGroupId);
        refreshData();
      }
    }
  }

  // 멤버 추가 Dialog
  private void showMemberInputDialog() {
    String input = JOptionPane.showInputDialog(this, "추가할 멤버를 입력하세요:", "멤버 추가", JOptionPane.PLAIN_MESSAGE);

    if (input != null && !input.trim().isEmpty()) {
      // 서버로 POST 요청 전송
      FetchStudyListAdd.sendPostMember(input, selectGroupId);
      refreshData();
    } else {
      JOptionPane.showMessageDialog(this, "입력된 값이 없습니다. 다시 입력해 주세요.", "오류", JOptionPane.ERROR_MESSAGE);
    }
  }

  // 레퍼런스 추가 Dialog
  private void showReferenceInputDialog() {
    JTextField reference_name = new JTextField();
    JTextField reference_url = new JTextField();

    Object[] inputFields = {
        "이름:", reference_name,
        "url:", reference_url,
    };

    int option = JOptionPane.showConfirmDialog(
        this,
        inputFields,
        "레퍼런스 추가",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE);

    if (option == JOptionPane.OK_OPTION) {
      String name = reference_name.getText().trim();
      String url = reference_url.getText().trim();

      if (name.isEmpty() || url.isEmpty()) {
        JOptionPane.showMessageDialog(this, "모든 필드를 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
      } else {
        // FetchStudyListGoal 클래스를 사용하여 서버로 POST 요청 전송
        FetchStudyListAdd.sendPostReference(name, url, selectGroupId);
        refreshData();
      }
    }
  }

}