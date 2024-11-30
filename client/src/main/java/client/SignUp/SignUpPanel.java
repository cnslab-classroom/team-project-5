package client.SignUp;

// 외부 import
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.Frame;
import client.Main.ClientMain;

public class SignUpPanel extends JPanel {
    public SignUpPanel(Frame parentFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // 수직 정렬

        // 상단 레이블
        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 50));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setForeground(Color.BLACK);

        // 아이디 필드
        JPanel idPanel = createInputField("id", true);
        idPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 비밀번호 필드
        JPanel passwordPanel = createInputField("password", true);
        passwordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 이름 필드
        JPanel namePanel = createInputField("name", true);
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 이메일 필드
        JPanel emailPanel = createInputField("email", true);
        emailPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 별명 필드
        JPanel nicknamePanel = createInputField("nickname", true);
        nicknamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 다음 버튼
        JButton nextButton = new JButton("next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 16));
        nextButton.setBackground(new Color(0, 153, 0)); // 초록색 버튼
        nextButton.setForeground(Color.WHITE); // 버튼 텍스트 색상
        nextButton.setFocusPainted(false); // 선택 효과 제거
        nextButton.setMaximumSize(new Dimension(150, 40)); // 버튼 크기 제한
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 컴포넌트 간 간격 추가
        add(Box.createRigidArea(new Dimension(0, 30))); // 상단 여백
        add(welcomeLabel); // 상단 텍스트 추가
        add(Box.createRigidArea(new Dimension(0, 20))); // 간격
        add(idPanel); // 아이디 패널 추가
        add(Box.createRigidArea(new Dimension(0, 30))); // 간격
        add(passwordPanel); // 비밀번호 패널 추가
        add(Box.createRigidArea(new Dimension(0, 30))); // 간격
        add(namePanel); // 이름 패널 추가
        add(Box.createRigidArea(new Dimension(0, 30))); // 간격
        add(emailPanel); // 이메일 패널 추가
        add(Box.createRigidArea(new Dimension(0, 30))); // 간격
        add(nicknamePanel); // 별명 패널 추가
        add(Box.createRigidArea(new Dimension(0, 30))); // 간격
        add(nextButton); // 다음 버튼 추가

        // 버튼 클릭 이벤트, 다음 넘어갈 페이지가 있는데 아직 안 만들어서 ClientMain()으로 넘어감
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.switchToPanel(new ClientMain());
            }
        });
    }

    private JPanel createInputField(String labelText, boolean isRequired) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        // 텍스트 레이블
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 별표 레이블 (빨간색)
        JLabel asteriskLabel = new JLabel("*");
        asteriskLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        asteriskLabel.setForeground(Color.RED);
        asteriskLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 레이블을 하나로 결합 (수평 배치)
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
        labelPanel.setBackground(Color.WHITE);
        labelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelPanel.add(label); // 메인 텍스트
        if (isRequired) {
            labelPanel.add(Box.createRigidArea(new Dimension(2, 0))); // 간격
            labelPanel.add(asteriskLabel); // 별표 추가
        }

        // 입력 필드
        JTextField textField = new JTextField(20);
        textField.setMaximumSize(new Dimension(300, 40));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));

        // 패널에 레이블과 입력 필드 추가
        panel.add(labelPanel); // 레이블 패널 추가
        panel.add(Box.createRigidArea(new Dimension(0, 5))); // 레이블과 필드 간 간격
        panel.add(textField); // 텍스트 필드 추가

        return panel;
    }
}
