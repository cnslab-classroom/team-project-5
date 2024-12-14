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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.Frame;
import client.Main.fetchData.SendPostSignUp;
import client.RoundedButton;

public class SignUpPanel extends JPanel {
    // 입력 필드
    private JTextField idField;
    private JTextField passwordField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField nicknameField;

    public SignUpPanel(Frame parentFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // 수직 정렬

        // 상단 레이블
        JLabel welcomeLabel = new JLabel("환영합니다!🤗");
        welcomeLabel.setFont(new Font("Paperlogy", Font.BOLD, 50));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setForeground(Color.BLACK);

        // 아이디 필드
        idField = new JTextField();
        JPanel idPanel = createInputField("아이디🔑", idField, true);
        idPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 비밀번호 필드
        passwordField = new JTextField();
        JPanel passwordPanel = createInputField("비밀번호🔒", passwordField, true);
        passwordPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 이름 필드
        nameField = new JTextField();
        JPanel namePanel = createInputField("이름🧑", nameField, true);
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 이메일 필드
        emailField = new JTextField();
        JPanel emailPanel = createInputField("이메일✉️", emailField, true);
        emailPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 별명 필드
        nicknameField = new JTextField();
        JPanel nicknamePanel = createInputField("별명🌟", nicknameField, true);
        nicknamePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 다음 버튼
        RoundedButton nextButton = new RoundedButton("회원가입", new Color(0, 153, 0));
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

        // 버튼 클릭 이벤트 - 회원가입 요청
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sendSignUpData())
                    parentFrame.switchToPanel(new TCAPanel(parentFrame, emailField.getText()));
            }
        });
    }

    // 입력 필드 생성 메서드
    private JPanel createInputField(String labelText, JTextField textField, boolean isRequired) {
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
        textField.setMaximumSize(new Dimension(300, 40));
        textField.setFont(new Font("Paperlogy", Font.PLAIN, 14));

        // 패널에 레이블과 입력 필드 추가
        panel.add(labelPanel); // 레이블 패널 추가
        panel.add(Box.createRigidArea(new Dimension(0, 5))); // 레이블과 필드 간 간격
        panel.add(textField); // 텍스트 필드 추가

        return panel;
    }

    // 회원가입 데이터 전송 메서드
    private boolean sendSignUpData() {
        String id = idField.getText();
        String password = passwordField.getText();
        String name = nameField.getText();
        String email = emailField.getText();
        String nickname = nicknameField.getText();
        boolean agreed = false; // 약관 동의 기본 값

        // 데이터 검증 (필요 시 추가)
        if (id.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty() || nickname.isEmpty()) {
            JOptionPane.showMessageDialog(null, "모든 필드를 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
            return false;
        }  

        // SendPostSignUp 호출
        boolean able = SendPostSignUp.sendPostSignUp(name, id, password, nickname, email, agreed);
        if (!able) {
            JOptionPane.showMessageDialog(null, "이전에 해당 이메일로 가입한 계정이 있습니다.", "회원가입 오류", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
}
