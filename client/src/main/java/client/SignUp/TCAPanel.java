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

public class TCAPanel extends JPanel {
    public TCAPanel(Frame parentFrame) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // 약관 텍스트 영역 생성
        JTextArea termsTextArea = new JTextArea();
        termsTextArea.setText(
                "[개인정보 처리 방침]\n\n" +
                "1. 개인정보 수집 항목 및 목적\n" +
                "수집 항목: 이름, 이메일, 전화번호\n" +
                "수집 목적: 회원 식별, 서비스 제공\n\n" +
                "2. 개인정보 수집 방법\n" +
                "회원가입 시 사용자 입력\n\n" +
                "3. 개인정보 보유 및 이용 기간\n" +
                "회원 탈퇴 시까지 보유합니다.\n"
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
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 동의 체크박스
        JCheckBox agreeCheckBox = new JCheckBox("개인정보 처리 방침에 동의합니다.");
        agreeCheckBox.setBackground(Color.WHITE);

        // 다음 버튼
        JButton nextButton = new JButton("다음");
        nextButton.setEnabled(false);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (agreeCheckBox.isSelected()) {
                    // JOptionPane 표시
                    int result = JOptionPane.showConfirmDialog(
                            parentFrame,
                            "다음 화면으로 이동합니다!",
                            "확인",
                            JOptionPane.OK_CANCEL_OPTION
                    );
                    if (result == JOptionPane.OK_OPTION) {
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
