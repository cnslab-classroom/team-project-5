package client.Main;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.Border;

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
        topPanel.setBorder(new EmptyBorder(0, 10, 10, 10)); // 외곽 테두리

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
        JPanel graphPanel = new JPanel(new GridLayout(7, 53, 2, 2));
        graphPanel.setBackground(new Color(240, 240, 240));
        Border outerBorder = new LineBorder(Color.GRAY, 2, true);
        Border innerBorder = new EmptyBorder(10, 10, 10, 10);
        graphPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder)); // 외곽 테두리

        // 그리드 셀 생성
        for (int i = 0; i < 7 * 53; i++) {
            JPanel cell = new JPanel();
            int value = (int) (Math.random() * 100); // 0~100 랜덤 값
            cell.setBackground(getColorForValue(value));
            graphPanel.add(cell);
        }
        return graphPanel;
    }

    private Color getColorForValue(int value) {
        int greenIntensity = (int) (255 * (value / 100.0)); // 0에서 255로 변환
        return new Color(0, greenIntensity, 0); // 초록색 톤
    }

    private JPanel createStatisticsPanel() {
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBorder(new EmptyBorder(20, 10, 20, 10)); // 패널 내부 여백
        statsPanel.setBackground(Color.WHITE);

        JLabel statsTitle = new JLabel("지금까지의 통계를 보여드릴게요! 📈");
        statsTitle.setFont(new Font("paperlogy", Font.PLAIN, 20));
        statsTitle.setBorder(new EmptyBorder(0, 0, 10, 0)); // 아래쪽 여백

        statsTitle.setHorizontalAlignment(SwingConstants.LEFT); // 왼쪽 정렬
        statsTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        statsPanel.add(statsTitle, BorderLayout.WEST);

        // 통계 리스트 패널
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(240, 240, 240));
        Border outerBorder = new LineBorder(Color.GRAY, 2, true);
        Border innerBorder = new EmptyBorder(0, 10, 0, 10);
        listPanel.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder)); // 외곽 테두리

        String[] tasks = { "1일 1백준 🖥", "신나는 방 청소 🧹", "기초영작문 노트정리 📝" };
        String[] progress = { "(55/94) 58.5%", "(11/13) 84.6%", "(18/52) 34.6%" };

        for (int i = 0; i < tasks.length; i++) {
            JPanel taskPanel = new JPanel(new BorderLayout());
            taskPanel.setBackground(new Color(240, 240, 240));

            JCheckBox checkBox = new JCheckBox(tasks[i]);
            checkBox.setFont(new Font("Arial", Font.PLAIN, 15));
            taskPanel.add(checkBox, BorderLayout.WEST);

            JLabel progressLabel = new JLabel(progress[i], SwingConstants.RIGHT);
            progressLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            taskPanel.add(progressLabel, BorderLayout.EAST);

            listPanel.add(taskPanel);
        }

        statsPanel.add(listPanel);
        return statsPanel;
    }

}