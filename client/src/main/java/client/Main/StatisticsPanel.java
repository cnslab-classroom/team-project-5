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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import client.Main.fetchData.FetchStatistics;
import client.Main.model.DailyStatistics;
import client.Main.model.Statistics;

public class StatisticsPanel extends JPanel {
    private FetchStatistics.StatisticsData statisticsData;

    public StatisticsPanel() {
        setLayout(new BorderLayout(10, 10)); // 패널 간 간격
        setBorder(new EmptyBorder(30, 30, 30, 30)); // 전체 여백 설정
        setBackground(Color.WHITE);

        // 서버에서 통계 데이터 가져오기
        statisticsData = FetchStatistics.fetchStatisticsData();

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

        // 0~100 사이의 값으로 점점 진한 초록색 생성
        for (int i = 0; i <= 100; i += 25) {
            JPanel colorPanel = new JPanel();
            colorPanel.setBackground(getColorForValue(i)); // 각 단계에 해당하는 색상
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

        // 날짜별 데이터를 Map으로 변환 (날짜 -> 달성도)
        Map<String, Double> achievementMap = new HashMap<>();
        for (Statistics stat : statisticsData.getStatics()) {
            achievementMap.put(stat.getDate(), stat.getAchievement());
        }

        // 서버에서 받은 날짜 리스트를 7x53 형태로 나누기
        List<String> dateList = new ArrayList<>(achievementMap.keySet());
        List<List<String>> weeklyGrid = new ArrayList<>();

        // 7x53으로 쪼개기
        int totalCols = 53;
        for (int i = 0; i < 7; i++) {
            List<String> weekRow = new ArrayList<>();
            for (int j = 0; j < totalCols; j++) {
                int index = j * 7 + i; // 순서대로 요일에 맞게 인덱스 계산
                if (index < dateList.size()) {
                    weekRow.add(dateList.get(index));
                } else {
                    weekRow.add(null); // 빈 값
                }
            }
            weeklyGrid.add(weekRow);
        }

        // 그리드 채우기
        for (int row = 0; row < 7; row++) { // 행 단위 (요일)
            for (int col = 0; col < 54; col++) { // 열 단위 (주)
                if (col == 0) {
                    // 첫 번째 열에 요일 레이블 추가
                    JLabel dayLabel = new JLabel(days[row], SwingConstants.CENTER);
                    dayLabel.setFont(new Font("paperlogy", Font.BOLD, 14));
                    dayLabel.setForeground(row >= 5 ? (row == 5 ? Color.BLUE : Color.RED) : Color.BLACK);
                    graphPanel.add(dayLabel);
                } else {
                    // 날짜에 맞춰 색상 설정
                    JPanel cell = new JPanel() {
                        @Override
                        public Dimension getPreferredSize() {
                            return new Dimension(10, 10); // 셀 크기
                        }
                    };

                    String date = weeklyGrid.get(row).get(col - 1);
                    if (date != null) {
                        double value = achievementMap.getOrDefault(date, 0.0);
                        cell.setBackground(getColorForValue(value));
                    } else {
                        cell.setBackground(new Color(220, 220, 220)); // 데이터가 없는 경우 회색
                    }

                    graphPanel.add(cell);
                }
            }
        }

        return graphPanel;
    }

    // 달성도에 따라 색상 반환
    private Color getColorForValue(double value) {
        int greenIntensity = Math.min((int) (255 * ((100.0 - value) / 100.0)), 255);
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

        List<Map<String, Object>> Data = new ArrayList<>();

        for (DailyStatistics dailyStatics : statisticsData.getDailyStatics()) {
            Map<String, Object> item = new HashMap<>();
            item.put("goal_text", dailyStatics.getGoalText());
            item.put("goal_date_achievement", dailyStatics.getGoalDateAchievement());
            item.put("goal_whole_date", dailyStatics.getGoalWholeDate());
            item.put("goal_percent", dailyStatics.getGoalPercent());
            Data.add(item);
        }

        for (Map<String, Object> item : Data) {
            JPanel taskPanel = new JPanel(new BorderLayout());
            taskPanel.setBackground(new Color(240, 240, 240));

            String goalText = (String) item.get("goal_text");

            // 값 변환: Double -> Integer
            int achieved = ((Number) item.get("goal_date_achievement")).intValue();
            int total = ((Number) item.get("goal_whole_date")).intValue();
            double percent = ((Number) item.get("goal_percent")).doubleValue();

            JLabel checkBox = new JLabel(goalText);
            checkBox.setFont(new Font("Paperlogy", Font.PLAIN, 15));
            taskPanel.add(checkBox, BorderLayout.WEST);

            // 목표 진행률 텍스트 생성
            int greenIntensity = Math.min((int) (255 * ((100 - percent) / 100.0)), 255);
            String progressText = String.format("(%d/%d) <span style='color: rgb(0,%d,0);'>%.1f%%</span>",
                    achieved,
                    total,
                    greenIntensity,
                    percent);

            // HTML을 사용해 JLabel 생성
            JLabel progressLabel = new JLabel("<html>" + progressText + "</html>", SwingConstants.RIGHT);
            progressLabel.setFont(new Font("Paperlogy", Font.PLAIN, 15));

            taskPanel.add(progressLabel, BorderLayout.CENTER);
            listPanel.add(taskPanel);
        }

        statsPanel.add(listPanel, BorderLayout.CENTER);
        return statsPanel;
    }

}