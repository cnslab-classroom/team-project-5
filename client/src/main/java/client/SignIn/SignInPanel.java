package client.SignIn;

// 외부 import
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.Frame;
import client.Main.ClientMain;
import client.Main.fetchData.SendPostSignIn;
import client.RoundedButton;

public class SignInPanel extends JPanel {
    private JTextField idField;
    private JTextField passwordField;

    public SignInPanel(Frame parentFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // 수직 정렬
        setBackground(new Color(240, 240, 240)); // 배경색 설정

        // 상단 레이블
        JLabel welcomeLabel = new JLabel("돌아오신걸 환영합니다!🤗");
        welcomeLabel.setFont(new Font("Paperlogy", Font.BOLD, 20));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setForeground(Color.BLACK); // 텍스트 색상

        // 아이디 필드
        JPanel idPanel = createInputField("아이디🔑", true);
        idField = (JTextField) idPanel.getComponent(2); // 입력 필드 가져오기

        // 비밀번호 필드
        JPanel passwordPanel = createInputField("비밀번호🔒", true);
        passwordField = (JTextField) passwordPanel.getComponent(2); // 입력 필드 가져오기

        // 다음 버튼
        RoundedButton nextButton = new RoundedButton("다음", new Color(0, 153, 0));
        nextButton.setFont(new Font("Paperlogy", Font.BOLD, 16));
        nextButton.setBackground(new Color(0, 153, 0)); // 초록색 버튼
        nextButton.setForeground(Color.WHITE); // 버튼 텍스트 색상
        nextButton.setMaximumSize(new Dimension(150, 40)); // 버튼 크기 제한
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 컴포넌트 간 간격 추가
        add(Box.createRigidArea(new Dimension(0, 30))); // 상단 여백
        add(welcomeLabel); // 상단 텍스트 추가
        add(Box.createRigidArea(new Dimension(0, 20))); // 간격
        add(idPanel); // 아이디 패널 추가
        add(Box.createRigidArea(new Dimension(0, 15))); // 간격
        add(passwordPanel); // 비밀번호 패널 추가
        add(Box.createRigidArea(new Dimension(0, 30))); // 간격
        add(nextButton); // 다음 버튼 추가

        // 버튼 클릭 이벤트: 로그인 로직 추가
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String signId = idField.getText().trim();
                String password = passwordField.getText().trim();

                if (signId.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(parentFrame, "아이디와 비밀번호를 모두 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 서버에 로그인 요청 전송
                Integer memberId = SendPostSignIn.sendPostSignIn(signId, password);

                if (memberId != null) {
                    // 로그인 성공: 메인 화면(ClientMain)으로 이동
                    parentFrame.switchToPanel(new ClientMain());
                }
            }
        });
    }

    private JPanel createInputField(String labelText, boolean isRequired) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));

        // 텍스트 레이블
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Paperlogy", Font.BOLD, 14));
        label.setBackground(new Color(240, 240, 240));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 별표 레이블 (빨간색)
        JLabel asteriskLabel = new JLabel("*");
        asteriskLabel.setFont(new Font("Paperlogy", Font.PLAIN, 14));
        asteriskLabel.setForeground(Color.RED);
        asteriskLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 레이블을 하나로 결합 (수평 배치)
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
        labelPanel.setBackground(new Color(240, 240, 240));
        labelPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelPanel.add(label); // 메인 텍스트
        if (isRequired) {
            labelPanel.add(Box.createRigidArea(new Dimension(2, 0))); // 간격
            labelPanel.add(asteriskLabel); // 별표 추가
        }

        // 입력 필드
        JTextField textField = new JTextField(20);
        textField.setMaximumSize(new Dimension(300, 40));
        textField.setFont(new Font("Paperlogy", Font.PLAIN, 14));

        // 패널에 레이블과 입력 필드 추가
        panel.add(labelPanel); // 레이블 패널 추가
        panel.add(Box.createRigidArea(new Dimension(0, 5))); // 레이블과 필드 간 간격
        panel.add(textField); // 텍스트 필드 추가

        return panel;
    }
}
