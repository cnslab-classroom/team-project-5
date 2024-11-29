package client;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class StatisticsPanel extends JPanel {
    public StatisticsPanel() {
        setLayout(new BorderLayout());

        // 상단 텍스트
        JLabel titleLabel = new JLabel("당신의 꾸준한 노력이 빛을 발하고 있어요! ✨", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // 중앙 그래프
        JPanel graphPanel = new JPanel();
        graphPanel.setLayout(new GridLayout(7, 53, 2, 2)); // 7행, 53열
        Random random = new Random();

        // 그래프 셀 추가
        for (int i = 0; i < 7 * 53; i++) {
            JPanel cell = new JPanel();
            int value = random.nextInt(101); // 0~100 랜덤 값
            cell.setBackground(getColorForValue(value));
            graphPanel.add(cell);
        }
        add(graphPanel, BorderLayout.CENTER);
    }

    // 색상 값 생성 (0 ~ 100 값에 따라 색상 반환)
    private Color getColorForValue(int value) {
        int greenIntensity = (int) (255 * (value / 100.0)); // 0에서 255로 변환
        return new Color(0, greenIntensity, 0); // 초록색 톤
    }
}