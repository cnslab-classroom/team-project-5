package client;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class RoundedButton extends JButton {
    private Color normalColor;   // 기본 배경색
    private Color hoverColor;    // 마우스 호버 시 배경색
    private Color borderColor;   // 테두리 색상

    // 사용자 지정 색상을 받는 생성자
    public RoundedButton(String text, Color baseColor) {
        super(text);

        // 사용자 지정 색상을 기반으로 기본 값 설정
        normalColor = baseColor;                         // 내부 색상
        hoverColor = baseColor.darker();                 // 호버 시 더 어두운 색상
        borderColor = baseColor.darker().darker();       // 테두리 색상 (더 어두운 색상)

        // 버튼 속성 설정
        setOpaque(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setForeground(Color.WHITE); // 텍스트 색상 (흰색)

        // 마우스 이벤트 추가
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // 마우스가 버튼 위로 올라왔을 때
                normalColor = hoverColor;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // 마우스가 버튼을 벗어났을 때
                normalColor = baseColor; // 기본 색상으로 복귀
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // 부드러운 모서리를 위해 안티앨리어싱 설정
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 버튼 배경 그리기
        g2.setColor(normalColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // 내부

        // 버튼 테두리 그리기
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20); // 외부 (테두리)

        // 버튼 텍스트 그리기
        g2.setColor(getForeground()); // 텍스트 색상
        FontMetrics fm = g2.getFontMetrics();
        int stringWidth = fm.stringWidth(getText());
        int stringHeight = fm.getAscent();
        g2.drawString(getText(), (getWidth() - stringWidth) / 2, (getHeight() + stringHeight) / 2 - 3);

        g2.dispose(); // Graphics2D 객체 해제
        super.paintComponent(g);
    }
}