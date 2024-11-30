package client.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GoalPanel extends JPanel {
    public GoalPanel() {
        // 패널 설정
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);

        add(Box.createRigidArea(new Dimension(0, 10)));

        // 상단 제목 텍스트
        JLabel titleLabel = new JLabel("오늘의 목표를 100% 달성했어요.");
        titleLabel.setFont(new Font("Paperlogy", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0, 128, 0)); // 초록색 텍스트
        titleLabel.setAlignmentX(CENTER_ALIGNMENT); // 가운데 정렬
        add(titleLabel);

        // 제목과 서브 텍스트 사이의 여백
        add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel subLabel = new JLabel("완벽하네요! 💯");
        subLabel.setFont(new Font("Paperlogy", Font.PLAIN, 14));
        subLabel.setAlignmentX(CENTER_ALIGNMENT); // 가운데 정렬
        add(subLabel);

        // 서브 텍스트와 체크리스트 패널 사이의 여백
        add(Box.createRigidArea(new Dimension(0, 20)));

        // 체크리스트와 플러스 버튼을 포함하는 패널 생성
        JPanel checklistContainer = new JPanel();
        checklistContainer.setBackground(Color.WHITE);
        checklistContainer.setLayout(new BoxLayout(checklistContainer, BoxLayout.Y_AXIS)); // 세로 정렬

        // 체크리스트와 버튼을 포함하는 상단 패널
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS)); // 수평 정렬

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
        checklistContainer.setMaximumSize(new Dimension(300, 400)); // 최대 크기 설정

        // 플러스 버튼과 체크리스트 사이의 간격
        checklistContainer.add(Box.createRigidArea(new Dimension(0, 10)));

        // 체크리스트 패널 생성
        JPanel checklistPanel = new JPanel();
        checklistPanel.setBackground(Color.WHITE);
        checklistPanel.setLayout(new BoxLayout(checklistPanel, BoxLayout.Y_AXIS)); // 세로 정렬

        // 체크박스 항목 생성
        JCheckBox checkBox1 = createCheckBox("1일 1백준 📄");
        JCheckBox checkBox2 = createCheckBox("신나는 방 청소 ✨");
        JCheckBox checkBox3 = createCheckBox("기초영작문 노트정리 📝");
        JCheckBox checkBox4 = createCheckBox("테스트1");
        JCheckBox checkBox5 = createCheckBox("테스트2");

        // 체크박스 추가
        checklistPanel.add(checkBox1);
        checklistPanel.add(Box.createRigidArea(new Dimension(0, 5))); // 체크박스 간 간격
        checklistPanel.add(checkBox2);
        checklistPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        checklistPanel.add(checkBox3);
        checklistPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        checklistPanel.add(checkBox4);
        checklistPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        checklistPanel.add(checkBox5);

        // 체크리스트 패널을 플러스 버튼 아래에 추가
        checklistContainer.add(checklistPanel);

        // 메인 패널에 컨테이너 추가
        checklistContainer.setAlignmentX(CENTER_ALIGNMENT); // 컨테이너 자체는 가운데 정렬
        add(checklistContainer);

        // 체크리스트와 하단 사이의 여백
        add(Box.createRigidArea(new Dimension(0, 20)));
    }

    // 체크박스를 생성하는 헬퍼 메서드
    private JCheckBox createCheckBox(String text) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setSelected(false);
        checkBox.setEnabled(true);
        checkBox.setFont(new Font("Paperlogy", Font.PLAIN, 14));
        checkBox.setBackground(Color.WHITE);
        return checkBox;
    }
}