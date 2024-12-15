package client.Main;

// 외부 import
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import client.Main.fetchData.SendPostGoal;
import client.Main.fetchData.SendPostSchedule;
import client.Main.fetchData.SendPutGoal;
import client.Main.fetchData.FetchGoalData;
import client.Main.fetchData.FetchHome;
import client.Main.fetchData.FetchHome.HomeData;
import client.Main.model.Goal;
import client.Main.model.Plan;

import java.util.List;

public class GoalPanel extends JPanel {
    FetchHome.HomeData homeData = FetchHome.fetchHomeData();
    private List<Goal> goals = FetchGoalData.fetchGoalData();

    public GoalPanel() {

        // 패널 설정
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        // 오늘의 목표
        JPanel goalSection1 = createGoalSection("오늘의 목표를 보여드릴게요.", "완벽하네요! 💯", new Color(0, 128, 0));
        add(goalSection1);

        // 다가오는 일정
        JPanel scheduleSection = createScheduleSection("다가오는 일정들을 확인하세요.", "중요한 시간을 놓치지 마세요! 🌟", new Color(0, 0, 128));
        add(scheduleSection);
    }

    // Goal Section 생성 메서드
    private JPanel createGoalSection(String titleText, String subtitleText, Color color) {
        JPanel goalSection = new JPanel();
        goalSection.setLayout(new BoxLayout(goalSection, BoxLayout.Y_AXIS));
        goalSection.setBackground(Color.WHITE);
        goalSection.add(Box.createRigidArea(new Dimension(0, 35)));

        // 상단 제목 텍스트
        JLabel titleLabel = new JLabel(titleText);
        titleLabel.setFont(new Font("Paperlogy", Font.BOLD, 24));
        titleLabel.setForeground(color); // 색상
        titleLabel.setAlignmentX(CENTER_ALIGNMENT); // 가운데 정렬
        goalSection.add(titleLabel);

        // 제목과 서브 텍스트 사이의 여백
        goalSection.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel subLabel = new JLabel(subtitleText);
        subLabel.setFont(new Font("Paperlogy", Font.PLAIN, 20));
        subLabel.setAlignmentX(CENTER_ALIGNMENT); // 가운데 정렬
        goalSection.add(subLabel);

        // 서브 텍스트와 체크리스트 패널 사이의 여백
        goalSection.add(Box.createRigidArea(new Dimension(0, 20)));

        // 체크리스트와 플러스 버튼을 포함하는 패널 생성
        JPanel checklistContainer = new JPanel();
        checklistContainer.setLayout(new BoxLayout(checklistContainer, BoxLayout.Y_AXIS)); // 세로 정렬
        checklistContainer.setBackground(new Color(240, 240, 240));
        checklistContainer.setBorder(new LineBorder(Color.GRAY, 2, true)); // 테두리 추가

        // 체크리스트와 버튼을 포함하는 상단 패널
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS)); // 수평 정렬
        topPanel.setBackground(new Color(240, 240, 240));

        // 상단 버튼 생성
        JButton addButton = new JButton("+");
        addButton.setFont(new Font("Paperlogy", Font.BOLD, 12));
        addButton.setPreferredSize(new Dimension(40, 40)); // 버튼 크기
        addButton.setFocusPainted(false); // 포커스 테두리 제거
        addButton.setAlignmentX(RIGHT_ALIGNMENT); // 오른쪽 정렬

        // 버튼과 체크리스트 상단 간격
        topPanel.add(Box.createHorizontalGlue()); // 오른쪽 정렬을 위해 간격 추가
        topPanel.add(addButton);

        // 상단 패널을 컨테이너에 추가
        checklistContainer.add(topPanel);
        checklistContainer.setMaximumSize(new Dimension(600, Integer.MAX_VALUE)); // 최대 크기 설정

        // 플러스 버튼과 체크리스트 사이의 간격
        checklistContainer.add(Box.createRigidArea(new Dimension(0, 10)));

        // 체크리스트 패널 생성
        JPanel checklistPanel = new JPanel();
        checklistPanel.setBackground(new Color(240, 240, 240));
        checklistPanel.setMaximumSize(new Dimension(600, Integer.MAX_VALUE));
        checklistPanel.setLayout(new BoxLayout(checklistPanel, BoxLayout.Y_AXIS)); // 세로 정렬

        for (Goal item : goals) {
            JCheckBox goal = new JCheckBox(" " + item.getText());
            goal.setBackground(new Color(240, 240, 240));
            goal.setBorder(new EmptyBorder(5, 10, 0, 10));
            goal.setFont(new Font("Paperlogy", Font.PLAIN, 15));
            checklistPanel.add(goal);
            System.out.println(item.getText());
        }

        // 버튼 클릭 이벤트
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGoalInputDialog(checklistPanel);
            }
        });

        // 체크리스트 패널을 플러스 버튼 아래에 추가
        checklistContainer.add(checklistPanel);

        // Goal Section에 체크리스트 추가
        goalSection.add(checklistContainer);

        // 체크리스트와 하단 사이의 여백
        goalSection.add(Box.createRigidArea(new Dimension(0, 20)));

        return goalSection;
    }

    // Schedule Section 생성 메서드
    private JPanel createScheduleSection(String titleText, String subtitleText, Color color) {
        JPanel scheduleSection = new JPanel();
        scheduleSection.setLayout(new BoxLayout(scheduleSection, BoxLayout.Y_AXIS));
        scheduleSection.setBackground(Color.WHITE);
        scheduleSection.add(Box.createRigidArea(new Dimension(0, 35)));

        // 상단 제목 텍스트
        JLabel titleLabel = new JLabel(titleText);
        titleLabel.setFont(new Font("Paperlogy", Font.BOLD, 24));
        titleLabel.setForeground(color); // 색상
        titleLabel.setAlignmentX(CENTER_ALIGNMENT); // 가운데 정렬
        scheduleSection.add(titleLabel);

        // 제목과 서브 텍스트 사이의 여백
        scheduleSection.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel subLabel = new JLabel(subtitleText);
        subLabel.setFont(new Font("Paperlogy", Font.PLAIN, 20));
        subLabel.setAlignmentX(CENTER_ALIGNMENT); // 가운데 정렬
        scheduleSection.add(subLabel);

        // 서브 텍스트와 체크리스트 패널 사이의 여백
        scheduleSection.add(Box.createRigidArea(new Dimension(0, 20)));

        // 체크리스트와 플러스 버튼을 포함하는 패널 생성
        JPanel checklistContainer = new JPanel();
        checklistContainer.setLayout(new BoxLayout(checklistContainer, BoxLayout.Y_AXIS)); // 세로 정렬
        checklistContainer.setBackground(new Color(240, 240, 240));
        checklistContainer.setBorder(new LineBorder(Color.GRAY, 2, true)); // 테두리 추가

        // 체크리스트와 버튼을 포함하는 상단 패널
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS)); // 수평 정렬
        topPanel.setBackground(new Color(240, 240, 240));

        // 상단 버튼 생성
        JButton addButton = new JButton("+");
        addButton.setFont(new Font("Paperlogy", Font.BOLD, 12));
        addButton.setPreferredSize(new Dimension(40, 40)); // 버튼 크기
        addButton.setFocusPainted(false); // 포커스 테두리 제거
        addButton.setAlignmentX(RIGHT_ALIGNMENT); // 오른쪽 정렬

        // 버튼과 체크리스트 상단 간격
        topPanel.add(Box.createHorizontalGlue()); // 오른쪽 정렬을 위해 간격 추가
        topPanel.add(addButton);

        // 상단 패널을 컨테이너에 추가
        checklistContainer.add(topPanel);
        checklistContainer.setMaximumSize(new Dimension(600, Integer.MAX_VALUE)); // 최대 크기 설정

        // 플러스 버튼과 체크리스트 사이의 간격
        checklistContainer.add(Box.createRigidArea(new Dimension(0, 10)));

        // 체크리스트 패널 생성
        JPanel checklistPanel = new JPanel();
        checklistPanel.setBackground(new Color(240, 240, 240));
        checklistPanel.setMaximumSize(new Dimension(600, Integer.MAX_VALUE));
        checklistPanel.setLayout(new BoxLayout(checklistPanel, BoxLayout.Y_AXIS)); // 세로 정렬

        for (Plan item : homeData.getPlans()) {
            JLabel goal = new JLabel(" " + item.getText());
            goal.setBackground(new Color(240, 240, 240));
            goal.setBorder(new EmptyBorder(5, 10, 0, 10));
            goal.setFont(new Font("Paperlogy", Font.PLAIN, 15));
            checklistPanel.add(goal);
            System.out.println(item.getText());
        }

        checklistPanel.revalidate();
        checklistPanel.repaint();

        // 버튼 클릭 이벤트
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showScheduleInputDialog(checklistPanel);
            }
        });

        // 체크리스트 패널을 플러스 버튼 아래에 추가
        checklistContainer.add(checklistPanel);

        // Schedule Section에 체크리스트 추가
        scheduleSection.add(checklistContainer);

        // 체크리스트와 하단 사이의 여백
        scheduleSection.add(Box.createRigidArea(new Dimension(0, 20)));

        return scheduleSection;
    }

    // 체크박스를 생성하는 헬퍼 메서드
    private JCheckBox createCheckBox(String text) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setSelected(false);
        checkBox.setEnabled(true);
        checkBox.setFont(new Font("Paperlogy", Font.PLAIN, 14));
        return checkBox;
    }

    // 목표 추가 Dialog
    private void showGoalInputDialog(JPanel checklistPanel) {
        String goalText = JOptionPane.showInputDialog(this, "추가할 목표를 입력하세요:", "목표 추가", JOptionPane.PLAIN_MESSAGE);
        if (goalText != null && !goalText.trim().isEmpty()) {
            String startDate = JOptionPane.showInputDialog(this, "목표 시작 날짜를 입력하세요 (YYYY-MM-DD):", "목표 시작 날짜",
                    JOptionPane.PLAIN_MESSAGE);
            String endDate = JOptionPane.showInputDialog(this, "목표 종료 날짜를 입력하세요 (YYYY-MM-DD):", "목표 종료 날짜",
                    JOptionPane.PLAIN_MESSAGE);

            if (startDate != null && !startDate.trim().isEmpty() && endDate != null && !endDate.trim().isEmpty()) {
                try {
                    // 서버로 목표 데이터 전송
                    Long goal_id = SendPostGoal.sendPostGoal(goalText, startDate, endDate);

                    // 새로운 체크박스 추가
                    JCheckBox newCheckBox = createCheckBox(goalText);
                    newCheckBox.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // 체크박스 클릭 시 서버로 목표 체크 데이터 전송
                            SendPutGoal.sendPutGoal(goal_id);
                        }
                    });

                    checklistPanel.add(newCheckBox);
                    checklistPanel.setAlignmentX(CENTER_ALIGNMENT); // 가운데 정렬
                    checklistPanel.add(Box.createRigidArea(new Dimension(0, 5))); // 간격 추가
                    checklistPanel.revalidate(); // UI 갱신
                    checklistPanel.repaint();

                    JOptionPane.showMessageDialog(this, "목표가 추가되었습니다: " + goalText);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "목표 추가 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "시작 날짜와 종료 날짜를 모두 입력해야 합니다.", "입력 오류", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // 일정 추가 Dialog
    private void showScheduleInputDialog(JPanel checklistPanel) {
        String plan_text = JOptionPane.showInputDialog(this, "추가할 일정을 입력하세요:", "일정 추가", JOptionPane.PLAIN_MESSAGE);
        if (plan_text != null && !plan_text.trim().isEmpty()) {
            String goal_date = JOptionPane.showInputDialog(this, "일정 시간을 입력하세요 (yyyy-MM-ddTHH:mm):", "일정 시간",
                    JOptionPane.PLAIN_MESSAGE);

            if (goal_date != null && !goal_date.trim().isEmpty()) {
                try {
                    // 서버로 일정 데이터 전송
                    SendPostSchedule.sendPostSchedule(plan_text, goal_date);

                    // 새로운 체크박스 추가
                    JCheckBox newCheckBox = createCheckBox(plan_text + " (" + goal_date + ")");
                    checklistPanel.add(newCheckBox);
                    checklistPanel.setAlignmentX(CENTER_ALIGNMENT); // 가운데 정렬
                    checklistPanel.add(Box.createRigidArea(new Dimension(0, 5))); // 간격 추가
                    checklistPanel.revalidate(); // UI 갱신
                    checklistPanel.repaint();

                    JOptionPane.showMessageDialog(this, "일정이 추가되었습니다: " + plan_text);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "일정 추가 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "일정 시간을 입력해야 합니다.", "입력 오류", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
