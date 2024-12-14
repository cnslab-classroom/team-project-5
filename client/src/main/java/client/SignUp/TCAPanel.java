package client.SignUp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import client.Frame;
import client.Main.fetchData.SendPostTCA;

public class TCAPanel extends JPanel {
    public TCAPanel(Frame parentFrame, String email) {
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        // 약관 텍스트 영역 생성
        JTextArea termsTextArea = new JTextArea();
        termsTextArea.setText(
            "[개인정보 처리 방침]\n\n" +
            "1. 개인정보 수집 항목 및 목적\n" +
            "당사는 다음과 같은 개인정보를 수집하며, 수집된 정보는 다음 목적을 위해 사용됩니다:\n" +
            "수집 항목: 이름, 이메일, 전화번호\n" +
            "수집 목적: 회원 식별, 서비스 제공, 맞춤형 콘텐츠 제공\n\n" +
            "2. 개인정보 수집 방법\n" +
            "당사는 다음과 같은 방법으로 개인정보를 수집합니다:\n" +
            "회원가입 시 사용자 입력\n" +
            "서비스 이용 과정에서 자동으로 수집되는 정보(쿠키, 로그 기록 등)\n\n" +
            "3. 개인정보 보유 및 이용 기간\n" +
            "회원 탈퇴 시까지 보유하며, 관계 법령에 따라 필요한 경우 일정 기간 동안 보관할 수 있습니다.\n\n" +
            "4. 개인정보의 제3자 제공\n" +
            "당사는 원칙적으로 개인정보를 제3자에게 제공하지 않습니다. 단, 법령에 의한 요구가 있을 경우에 한해 제공될 수 있습니다.\n\n" +
            "5. 개인정보의 처리 위탁\n" +
            "당사는 개인정보 처리를 위해 외부 업체에 위탁하지 않으며, 추후 위탁이 발생할 경우 이를 명확하게 고지하겠습니다.\n\n" +
            "6. 이용자의 권리\n" +
            "이용자는 언제든지 본인의 개인정보에 대해 열람, 수정, 삭제, 처리 정지를 요청할 수 있습니다. 요청은 harubi@gmail.com로 접수해 주시기 바랍니다.\n\n" +
            "7. 개인정보의 안전성 확보 조치\n" +
            "당사는 개인정보를 안전하게 보호하기 위해 다음과 같은 조치를 취하고 있습니다:\n" +
            "암호화 기술을 통한 개인정보 보호\n" +
            "접근 통제 시스템을 통한 보안 강화\n\n" +
            "8. 개인정보 보호 책임자\n" +
            "이름: 김수오\n" +
            "연락처: 010-2187-6991\n" +
            "이메일: vermillion1696@gmail.com\n\n" +
            "9. 개인정보 처리 방침 변경\n" +
            "본 방침의 내용이 변경될 경우, 변경 사항을 사전에 공지하고, 중요한 사항일 경우 별도의 동의를 받을 예정입니다."
        );
        termsTextArea.setEditable(false);
        termsTextArea.setLineWrap(true);
        termsTextArea.setWrapStyleWord(true);
        termsTextArea.setMargin(new Insets(10, 10, 10, 10));

        // 스크롤 패널 생성
        JScrollPane scrollPane = new JScrollPane(termsTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // 하단 체크박스와 버튼 패널 생성
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 동의 체크박스
        JCheckBox agreeCheckBox = new JCheckBox("개인정보 처리 방침에 동의합니다.");
        agreeCheckBox.setBackground(new Color(240, 240, 240));

        // 다음 버튼
        JButton nextButton = new JButton("다음");
        nextButton.setEnabled(false);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (agreeCheckBox.isSelected()) {
                    // 확인 메시지 표시
                    int result = JOptionPane.showConfirmDialog(
                            parentFrame,
                            "개인정보 처리 방침에 동의하시겠습니까?",
                            "확인",
                            JOptionPane.OK_CANCEL_OPTION
                    );
                    if (result == JOptionPane.OK_OPTION) {
                        // 서버에 동의 데이터 전송
                        boolean agreed = true; // 항상 true 값 전송
                        SendPostTCA.sendPostTCA(email, agreed); // email과 agreed 전달
                        parentFrame.switchToPanel(new SuccessSignUpPanel(parentFrame));
                    }
                }
            }
        });

        // 체크박스 상태에 따라 버튼 활성화
        agreeCheckBox.addActionListener(e -> nextButton.setEnabled(agreeCheckBox.isSelected()));

        // 하단 패널에 컴포넌트 추가
        bottomPanel.add(agreeCheckBox, BorderLayout.WEST);
        bottomPanel.add(nextButton, BorderLayout.EAST);

        // 메인 패널에 컴포넌트 추가
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
