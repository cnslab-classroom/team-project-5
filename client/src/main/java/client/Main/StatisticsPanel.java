package client.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class StatisticsPanel extends JPanel {

    public StatisticsPanel() {
        setLayout(new BorderLayout(10, 10)); // 패널 간 간격
        setBorder(new EmptyBorder(30, 30, 30, 30)); // 전체 여백 설정
        setBackground(Color.WHITE);

        // 1. 상단 영역 (텍스트와 그래프)
        JPanel topPanel = createTopPanel();
        add(topPanel, BorderLayout.NORTH);

        // 2. 중앙 영역 (통계 정보)
        JPanel middlePanel = createStatisticsPanel();
        add(middlePanel, BorderLayout.CENTER);

    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        // topPanel.setBorder(new EmptyBorder(0, 10, 10, 10)); // 외곽 테두리

        // 텍스트와 범례를 포함한 패널
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(Color.WHITE);
        textPanel.setBorder(new EmptyBorder(0, 10, 10, 10)); // 내부 여백

        JLabel titleLabel = new JLabel("<html>당신의 꾸준한 노력이<br>빛을 발하고 있어요! ✨</html>");
        titleLabel.setFont(new Font("paperlogy", Font.PLAIN, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT); // 왼쪽 정렬
        textPanel.add(titleLabel, BorderLayout.WEST);

        // 범례 패널
        JPanel legendPanel = new JPanel();
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
        legendPanel.setPreferredSize(new Dimension(30, 120)); // 범례 크기 조정
        legendPanel.setBackground(Color.WHITE);

        JLabel labelMin = new JLabel("0");
        labelMin.setAlignmentX(Component.CENTER_ALIGNMENT);
        legendPanel.add(labelMin);

        for (int i = 0; i <= 100; i += 25) {
            JPanel colorPanel = new JPanel();
            colorPanel.setBackground(getColorForValue(i));
            colorPanel.setPreferredSize(new Dimension(10, 20));
            legendPanel.add(colorPanel);
        }

        JLabel labelMax = new JLabel("100");
        labelMax.setAlignmentX(Component.CENTER_ALIGNMENT);
        legendPanel.add(labelMax);

        textPanel.add(legendPanel, BorderLayout.EAST);

        topPanel.add(textPanel, BorderLayout.NORTH);

        // 그래프 패널
        JPanel graphPanel = createGraphPanel();

        topPanel.add(graphPanel, BorderLayout.SOUTH);

        return topPanel;
    }

    private JPanel createGraphPanel() {
        JPanel graphPanel = new JPanel(new GridLayout(7, 54, 2, 2)); // 7행 54열
        graphPanel.setBackground(new Color(240, 240, 240));
        Border outerBorder = new LineBorder(Color.GRAY, 2, true);
        Border innerBorder = new EmptyBorder(10, 10, 10, 10);
        graphPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        // 요일 배열
        String[] days = { "M", "T", "W", "T", "F", "S", "S" };

        // 그리드 생성
        for (int row = 0; row < 7; row++) {
            // 1. 각 행의 첫 번째 셀에 요일 추가
            JLabel dayLabel = new JLabel(days[row], SwingConstants.CENTER);
            dayLabel.setFont(new Font("Arial", Font.BOLD, 14));

            // 토요일과 일요일 색상 설정
            if (row == 5) {
                dayLabel.setForeground(Color.BLUE); // 토요일
            } else if (row == 6) {
                dayLabel.setForeground(Color.RED); // 일요일
            } else {
                dayLabel.setForeground(Color.BLACK); // 기본 요일
            }

            graphPanel.add(dayLabel); // 요일 추가

            // 2. 나머지 53개의 셀 추가
            for (int col = 0; col < 53; col++) {
                JPanel cell = new JPanel() {
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(2, 2); // 셀 크기 고정
                    }
                };
                int value = (int) (Math.random() * 100); // 0~100 랜덤 값
                cell.setBackground(getColorForValue(value));
                graphPanel.add(cell);
            }
        }

        return graphPanel;
    }

    private Color getColorForValue(int value) {
        int greenIntensity = (int) (255 * (value / 100.0)); // 0에서 255로 변환
        return new Color(0, greenIntensity, 0); // 초록색 톤
    }

    public JPanel createStatisticsPanel() {
        JPanel statsPanel = new JPanel(new BorderLayout());
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(new EmptyBorder(10, 0, 0, 0)); // 내부 여백

        JLabel statsTitle = new JLabel("지금까지의 통계를 보여드릴게요! 📈");
        statsTitle.setFont(new Font("paperlogy", Font.PLAIN, 20));
        statsTitle.setBorder(new EmptyBorder(0, 0, 10, 0)); // 아래쪽 여백

        statsTitle.setHorizontalAlignment(SwingConstants.LEFT); // 왼쪽 정렬
        statsTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        statsPanel.add(statsTitle, BorderLayout.NORTH);

        // 통계 리스트 패널
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(240, 240, 240));
        Border outerBorder = new LineBorder(Color.GRAY, 2, true);
        Border innerBorder = new EmptyBorder(0, 10, 0, 10);
        listPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder)); // 외곽 테두리

        // Mock 데이터 생성
        List<Map<String, Object>> mockData = new ArrayList<>();

        Map<String, Object> item1 = new HashMap<>();
        item1.put("goal_text", "1일 1백준wnswns");
        item1.put("goal_date_achievement", 55);
        item1.put("goal_whole_date", 94);
        item1.put("goal_percent", 58.5);
        mockData.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("goal_text", "신나는 방 청소");
        item2.put("goal_date_achievement", 11);
        item2.put("goal_whole_date", 13);
        item2.put("goal_percent", 84.6);
        mockData.add(item2);

        Map<String, Object> item3 = new HashMap<>();
        item3.put("goal_text", "기초영작문 노트정리");
        item3.put("goal_date_achievement", 18);
        item3.put("goal_whole_date", 52);
        item3.put("goal_percent", 34.6);
        mockData.add(item3);

        // 데이터 기반으로 리스트 구성
        for (Map<String, Object> item : mockData) {
            JPanel taskPanel = new JPanel(new BorderLayout());
            taskPanel.setBackground(new Color(240, 240, 240));

            String goalText = (String) item.get("goal_text");
            int achieved = (int) item.get("goal_date_achievement");
            int total = (int) item.get("goal_whole_date");
            double percent = (double) item.get("goal_percent");

            JCheckBox checkBox = new JCheckBox(goalText);
            checkBox.setFont(new Font("Paperlogy", Font.PLAIN, 15));
            taskPanel.add(checkBox, BorderLayout.WEST);

            // 목표 진행률 텍스트 생성
            String progressText = String.format("(%d/%d) <span style='color: rgb(0,%d,0);'>%.1f%%</span>",
                    achieved,
                    total,
                    (int) (255 * (percent / 100.0)), // percent 값에 따른 색상
                    percent);

            // HTML을 사용해 JLabel 생성
            JLabel progressLabel = new JLabel("<html>" + progressText + "</html>", SwingConstants.RIGHT);
            progressLabel.setFont(new Font("Paperlogy", Font.PLAIN, 15));

            // 패널에 레이블 추가
            taskPanel.add(progressLabel, BorderLayout.CENTER);

            listPanel.add(taskPanel);
        }

        statsPanel.add(listPanel, BorderLayout.CENTER);
        return statsPanel;
    }

}