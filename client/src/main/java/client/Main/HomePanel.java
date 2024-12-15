package client.Main;

// 외부 import
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import client.Main.fetchData.FetchGoalData;
import client.Main.fetchData.FetchHome;
import client.Main.fetchData.FetchHome.HomeData;
import client.Main.model.Goal;
import client.Main.model.StudyItem;

public class HomePanel extends JPanel {
    private HomeData homeData = FetchHome.fetchHomeData();
    private List<Goal> goals = FetchGoalData.fetchGoalData();
    private Border outerBorder = new LineBorder(Color.GRAY, 2, true);
    private Border innerBorder = new EmptyBorder(0, 10, 0, 10);

    public HomePanel() {
        setLayout(new BorderLayout()); // BorderLayout 사용

        // 메인 텍스트와 명언을 담는 패널
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // 수직 정렬
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 35)));

        // 메인 텍스트
        JPanel homeTextPanel = new JPanel();
        homeTextPanel.setLayout(new BoxLayout(homeTextPanel, BoxLayout.X_AXIS));
        homeTextPanel.setBackground(Color.WHITE);

        JLabel homeText1 = new JLabel("오늘도 빛나는 하루입니다, ");
        homeText1.setFont(new Font("Paperlogy", Font.BOLD, 20));

        JLabel homeText2 = new JLabel(homeData.getNickname());
        homeText2.setFont(new Font("Paperlogy", Font.BOLD, 20));
        homeText2.setForeground(Color.BLUE);

        JLabel homeText3 = new JLabel("님! 무엇을 먼저 시작해볼까요?");
        homeText3.setFont(new Font("Paperlogy", Font.BOLD, 20));

        homeTextPanel.add(homeText1);
        homeTextPanel.add(homeText2);
        homeTextPanel.add(homeText3);

        // 명언 텍스트
        JLabel HomeQuote = new JLabel(
                "<html><i>" + homeData.getSaying().getText() + "</i> - " + homeData.getSaying().getSpeaker()
                        + "</html>",
                JLabel.CENTER);
        HomeQuote.setFont(new Font("Paperlogy", Font.ITALIC, 13));
        HomeQuote.setBackground(new Color(240, 240, 240));
        HomeQuote.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        HomeQuote.setOpaque(true);
        HomeQuote.setAlignmentX(CENTER_ALIGNMENT);
        HomeQuote.setHorizontalAlignment(JLabel.CENTER); // 수평 가운데 정렬
        HomeQuote.setVerticalAlignment(JLabel.CENTER); // 수직 가운데 정렬
        HomeQuote.setMaximumSize(new Dimension(600, Integer.MAX_VALUE));

        // 목표 섹션
        JPanel goalPanel = new JPanel();
        goalPanel.setLayout(new BoxLayout(goalPanel, BoxLayout.Y_AXIS));
        goalPanel.setBackground(Color.WHITE);
        goalPanel.setAlignmentX(CENTER_ALIGNMENT); // 패널 중앙 정렬

        JLabel goalTitle = new JLabel("계획한 대로 차근차근, ");
        goalTitle.setFont(new Font("Paperlogy", Font.BOLD, 20));

        JLabel goalHighlight = new JLabel("목표");
        goalHighlight.setFont(new Font("Paperlogy", Font.BOLD, 20));
        goalHighlight.setForeground(Color.GREEN);

        JLabel goalTitleEnd = new JLabel("들을 확인해보세요! ✅");
        goalTitleEnd.setFont(new Font("Paperlogy", Font.BOLD, 20));

        JPanel goalTitlePanel = new JPanel();
        goalTitlePanel.setLayout(new BoxLayout(goalTitlePanel, BoxLayout.X_AXIS));
        goalTitlePanel.setBackground(Color.WHITE);
        goalTitlePanel.add(goalTitle);
        goalTitlePanel.add(goalHighlight);
        goalTitlePanel.add(goalTitleEnd);

        JPanel goalBox = new JPanel();
        goalBox.setLayout(new BoxLayout(goalBox, BoxLayout.Y_AXIS));
        goalBox.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        goalBox.setBackground(new Color(240, 240, 240));
        goalBox.setAlignmentX(CENTER_ALIGNMENT);
        // setMaximumSize 제거
        goalBox.setMaximumSize(new Dimension(600, Integer.MAX_VALUE));

        for (Goal item : goals) {
            JLabel goal = new JLabel(" " + item.getText());
            goal.setBackground(new Color(240, 240, 240));
            goal.setBorder(new EmptyBorder(5, 10, 0, 10));
            goal.setFont(new Font("Paperlogy", Font.PLAIN, 15));
            goalBox.add(goal);
            System.out.println(item.getText());
        }

        goalBox.revalidate();
        goalBox.repaint();

        goalPanel.add(goalTitlePanel);
        goalPanel.add(goalBox);
        // 일정 섹션
        JPanel schedulePanel = new JPanel();
        schedulePanel.setLayout(new BoxLayout(schedulePanel, BoxLayout.Y_AXIS));
        schedulePanel.setBackground(Color.WHITE);
        schedulePanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel scheduleTitle = new JLabel("다가오는 ");
        scheduleTitle.setFont(new Font("Paperlogy", Font.BOLD, 20));

        JLabel scheduleHighlight = new JLabel("일정");
        scheduleHighlight.setFont(new Font("Paperlogy", Font.BOLD, 20));
        scheduleHighlight.setForeground(Color.BLUE);

        JLabel scheduleTitleEnd = new JLabel("들을 확인해보세요. 중요한 시간을 놓치지 마세요! ⏰");
        scheduleTitleEnd.setFont(new Font("Paperlogy", Font.BOLD, 20));

        JPanel scheduleTitlePanel = new JPanel();
        scheduleTitlePanel.setLayout(new BoxLayout(scheduleTitlePanel, BoxLayout.X_AXIS));
        scheduleTitlePanel.setBackground(Color.WHITE);
        scheduleTitlePanel.add(scheduleTitle);
        scheduleTitlePanel.add(scheduleHighlight);
        scheduleTitlePanel.add(scheduleTitleEnd);

        JPanel scheduleBox = new JPanel();
        scheduleBox.setLayout(new BoxLayout(scheduleBox, BoxLayout.Y_AXIS));
        scheduleBox.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        scheduleBox.setBackground(new Color(240, 240, 240));
        scheduleBox.setAlignmentX(CENTER_ALIGNMENT);
        scheduleBox.setMaximumSize(new Dimension(600, Integer.MAX_VALUE));

        for (int i = 0; i < homeData.getPlans().size(); i++) {
            JLabel schedule = new JLabel(
                    homeData.getPlans().get(i).getText() + "  " + homeData.getPlans().get(i).getDate());
            schedule.setFont(new Font("Paperlogy", Font.PLAIN, 15));
            schedule.setBorder(new EmptyBorder(5, 10, 0, 10));
            schedule.setBackground(new Color(240, 240, 240));
            scheduleBox.add(schedule);
        }

        schedulePanel.add(scheduleTitlePanel);
        schedulePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        schedulePanel.add(scheduleBox);
        schedulePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // 학습 목표 섹션
        JPanel studyPanel = new JPanel();
        studyPanel.setLayout(new BoxLayout(studyPanel, BoxLayout.Y_AXIS));
        studyPanel.setBackground(Color.WHITE);
        studyPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel studyTitle = new JLabel("시험기간도 오케이! 친구들과 함께 ");
        studyTitle.setFont(new Font("Paperlogy", Font.BOLD, 20));

        JLabel studyHighlight = new JLabel("학습 목표");
        studyHighlight.setFont(new Font("Paperlogy", Font.BOLD, 20));
        studyHighlight.setForeground(Color.RED);

        JLabel studyTitleEnd = new JLabel("를 설정해봐요! 💯");
        studyTitleEnd.setFont(new Font("Paperlogy", Font.BOLD, 20));

        JPanel studyTitlePanel = new JPanel();
        studyTitlePanel.setLayout(new BoxLayout(studyTitlePanel, BoxLayout.X_AXIS));
        studyTitlePanel.setBackground(Color.WHITE);
        studyTitlePanel.add(studyTitle);
        studyTitlePanel.add(studyHighlight);
        studyTitlePanel.add(studyTitleEnd);

        JPanel studyBox = new JPanel();
        studyBox.setLayout(new BoxLayout(studyBox, BoxLayout.Y_AXIS));
        studyBox.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        studyBox.setBackground(new Color(240, 240, 240));
        studyBox.setAlignmentX(CENTER_ALIGNMENT);
        studyBox.setMaximumSize(new Dimension(600, Integer.MAX_VALUE));

        for (StudyItem item : homeData.getStudies()) {
            if (item.getDate() != null && !item.getDate().isEmpty() &&
                    item.getText() != null && !item.getText().isEmpty()) {
                JLabel study = new JLabel("📖 ~" + item.getDate() + "  " + item.getText());
                study.setBackground(new Color(240, 240, 240));
                study.setBorder(new EmptyBorder(5, 10, 0, 10));
                study.setFont(new Font("Paperlogy", Font.PLAIN, 15));
                studyBox.add(study);
            }
        }

        studyPanel.add(studyTitlePanel);
        studyPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        studyPanel.add(studyBox);
        studyPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // 중앙 패널에 모든 섹션 추가
        centerPanel.add(homeTextPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(HomeQuote);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(goalPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(schedulePanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(studyPanel);

        // 중앙 패널 추가
        add(centerPanel, BorderLayout.CENTER); // 중앙 패널
    }
}
