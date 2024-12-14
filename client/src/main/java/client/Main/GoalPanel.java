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
import javax.swing.border.LineBorder;

import client.Main.fetchData.SendPostGoal;

public class GoalPanel extends JPanel {
    public GoalPanel() {
        // 패널 설정
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        add(Box.createRigidArea(new Dimension(0, 10)));

        // 상단 제목 텍스트
        JLabel titleLabel = new JLabel("오늘의 목표를 100% 달성했어요.");
        titleLabel.setFont(new Font("Paperlogy", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 128, 0)); // 초록색 텍스트
        titleLabel.setAlignmentX(CENTER_ALIGNMENT); // 가운데 정렬
        add(titleLabel);

        // 제목과 서브 텍스트 사이의 여백
        add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel subLabel = new JLabel("완벽하네요! 💯");
        subLabel.setFont(new Font("Paperlogy", Font.PLAIN, 20));
        subLabel.setAlignmentX(CENTER_ALIGNMENT); // 가운데 정렬
        add(subLabel);

        // 서브 텍스트와 체크리스트 패널 사이의 여백
        add(Box.createRigidArea(new Dimension(0, 20)));

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
        checklistContainer.setMaximumSize(new Dimension(300, 400)); // 최대 크기 설정

        // 플러스 버튼과 체크리스트 사이의 간격
        checklistContainer.add(Box.createRigidArea(new Dimension(0, 10)));

        // 체크리스트 패널 생성
        JPanel checklistPanel = new JPanel();
        checklistPanel.setBackground(new Color(240, 240, 240));
        checklistPanel.setLayout(new BoxLayout(checklistPanel, BoxLayout.Y_AXIS)); // 세로 정렬

        // 버튼 클릭 이벤트, 약관 동의 패널로 전환
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGoalInputDialog(checklistPanel);
            }
        });
        
        // 체크박스 항목 생성 (임시)
        JCheckBox checkBox1 = createCheckBox("1일 1백준 📄");
        JCheckBox checkBox2 = createCheckBox("신나는 방 청소 ✨");
        JCheckBox checkBox3 = createCheckBox("기초영작문 노트정리 📝");

        // 체크박스 추가
        checklistPanel.add(checkBox1);
        checklistPanel.add(Box.createRigidArea(new Dimension(0, 5))); // 체크박스 간 간격
        checklistPanel.add(checkBox2);
        checklistPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        checklistPanel.add(checkBox3);

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
        return checkBox;
    }

    // 목표 추가 Dialog
    private void showGoalInputDialog(JPanel checklistPanel) {
        String goalText = JOptionPane.showInputDialog(this, "추가할 목표를 입력하세요:", "목표 추가", JOptionPane.PLAIN_MESSAGE);
        if (goalText != null && !goalText.trim().isEmpty()) {
            String startDate = JOptionPane.showInputDialog(this, "목표 시작 날짜를 입력하세요 (YYYY-MM-DD):", "목표 시작 날짜", JOptionPane.PLAIN_MESSAGE);
            String endDate = JOptionPane.showInputDialog(this, "목표 종료 날짜를 입력하세요 (YYYY-MM-DD):", "목표 종료 날짜", JOptionPane.PLAIN_MESSAGE);

            if (startDate != null && !startDate.trim().isEmpty() && endDate != null && !endDate.trim().isEmpty()) {
                try {
                    // 서버로 목표 데이터 전송
                    SendPostGoal.sendPostGoal(goalText, startDate, endDate);

                    // 새로운 체크박스 추가
                    JCheckBox newCheckBox = createCheckBox(goalText);
                    checklistPanel.add(newCheckBox);
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
}
