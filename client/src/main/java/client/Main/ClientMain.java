package client.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientMain extends JPanel {
  private JPanel currentPanel;

  public ClientMain() {

    setLayout(new BorderLayout());

    // 초기 화면 설정 (Main 화면)
    currentPanel = new HomePanel();
    add(currentPanel, BorderLayout.CENTER);

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

    // 버튼 클릭 이벤트 추가
    // 버튼 클릭 이벤트 추가
    ActionListener buttonListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // 현재 패널을 제거하고 새로운 패널로 교체
        remove(currentPanel);

        if (e.getSource() == homeButton) {
          currentPanel = new HomePanel();
        } else if (e.getSource() == goalButton) {
          currentPanel = new GoalPanel();
        } else if (e.getSource() == profileButton) {
          currentPanel = new ProfilePanel();
        } else if (e.getSource() == statisticsButton) {
          currentPanel = new StatisticsPanel();
        } else if (e.getSource() == listButton) {
          currentPanel = new ListPanel();
        }

        // 새로운 패널 추가 및 화면 갱신
        add(currentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
      }
    };

    homeButton.addActionListener(buttonListener);
    profileButton.addActionListener(buttonListener);
    statisticsButton.addActionListener(buttonListener);
    goalButton.addActionListener(buttonListener);
    listButton.addActionListener(buttonListener);

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