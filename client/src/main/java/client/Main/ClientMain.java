package client.Main;

// 외부 import
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// 내부 import
import client.Frame;
import client.Main.StatisticsPanel;

public class ClientMain extends JPanel {
  public ClientMain(Frame parentFrame) {

    // 바텀 내비게이션 바
    JPanel bottomNavBar = new JPanel();
    bottomNavBar.setLayout(new GridLayout(1, 5));

    // 이미지 로드 (클래스패스 기반)
    // ImageIcon homeIcon = new
    // ImageIcon("src/main/resources/images/lgcns_DevOn_그림.png");
    JButton statisticsButton = new JButton("통계");
    statisticsButton.setPreferredSize(new Dimension(50, 50));
    JButton goalButton = new JButton("목표");
    goalButton.setPreferredSize(new Dimension(50, 50));
    JButton homeButton = new JButton("홈");
    homeButton.setPreferredSize(new Dimension(50, 50));
    JButton listButton = new JButton("스터디 목록");
    listButton.setPreferredSize(new Dimension(50, 50));
    JButton profileButton = new JButton("프로필");
    profileButton.setPreferredSize(new Dimension(50, 50));

    // signUpButton 클릭 이벤트
    statisticsButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        parentFrame.switchToPanel(new StatisticsPanel(parentFrame));
      }
    });

    // 버튼을 바텀 내비게이션 바에 추가
    bottomNavBar.add(statisticsButton);
    bottomNavBar.add(goalButton);
    bottomNavBar.add(homeButton);
    bottomNavBar.add(listButton);
    bottomNavBar.add(profileButton);

    // 구성 요소를 JFrame에 추가
    add(bottomNavBar, BorderLayout.SOUTH);

    setVisible(true);
  }
}