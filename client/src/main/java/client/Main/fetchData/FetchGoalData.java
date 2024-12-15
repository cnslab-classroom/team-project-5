package client.Main.fetchData;

import client.Main.model.Goal;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchGoalData {

  public static List<Goal> fetchGoalData() {
    List<Goal> goals = new ArrayList<>();

    try {
      // 서버 URL 설정
      URL url = new URL("http://localhost:8080/plan/1/daily");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");

      // 서버 응답 확인
      int responseCode = conn.getResponseCode();
      System.out.println("서버 응답 코드: " + responseCode);

      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
      StringBuilder response = new StringBuilder();
      String line;

      while ((line = br.readLine()) != null) {
        response.append(line);
      }
      br.close();

      // 서버 응답 데이터 출력
      System.out.println("서버 응답 데이터: " + response.toString());

      // JSON 데이터 파싱
      parseGoalJson(response.toString(), goals);

    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "서버에서 데이터를 가져오지 못했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }

    return goals;
  }

  private static void parseGoalJson(String json, List<Goal> goals) {
    try {
      // "goal" 배열 추출
      String goalsSection = json.split("\"goal\":\\[")[1].split("\\]")[0];
      String[] goalItems = goalsSection.split("\\},\\{");

      for (String goalItem : goalItems) {
        goalItem = goalItem.replace("{", "").replace("}", ""); // 중괄호 제거
        String[] keyValues = goalItem.split(",");

        String goalText = null;
        Boolean goalStatus = null;
        Long goalId = null;

        for (String pair : keyValues) {
          String[] keyValue = pair.split(":", 2);
          String key = keyValue[0].trim().replace("\"", "");
          String value = keyValue[1].trim().replace("\"", "");

          switch (key) {
            case "goal_text":
              goalText = value;
              break;
            case "goal_status":
              goalStatus = Boolean.parseBoolean(value);
              break;
            case "goal_id":
              goalId = Long.parseLong(value);
              break;
          }
        }

        if (goalText != null && goalStatus != null && goalId != null) {
          goals.add(new Goal(goalText, goalStatus, goalId));
          System.out.println("Goal Parsed: " + goalText + " | Status: " + goalStatus + " | ID: " + goalId);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "JSON 데이터를 파싱하는 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }
  }
}