package client.Main.fetchData;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import client.Main.model.*;

public class FetchHome {

  public static HomeData fetchHomeData() {
    HomeData homeData = new HomeData();

    try {
      // 서버 URL 설정
      URL url = new URL("http://localhost:8080/plan/1");
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
      parseHomeJson(response.toString(), homeData);

    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "서버에서 데이터를 가져오지 못했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }

    return homeData;
  }

  private static void parseHomeJson(String json, HomeData homeData) {
    try {
      // nickname 파싱
      String nickname = extractValue(json, "nickname");
      homeData.setNickname(nickname);

      // // "goals" 배열 파싱
      // String goalsSection = json.split("\"goals\":\\[")[1].split("\\]")[0];
      // String[] goals = goalsSection.split("\\},\\{");

      // for (String goal : goals) {
      // goal = goal.replace("{", "").replace("}", ""); // 중괄호 제거
      // String text = extractValue(goal, "goal_text");

      // // goalStatuses 배열 전체 값 가져오기
      // String statusesSection =
      // goal.split("\"goalStatuses\":\\[")[1].split("\\]")[0];
      // String[] statusArray = statusesSection.split(",");

      // // status 리스트 생성
      // List<Boolean> statusList = new ArrayList<>();
      // for (String status : statusArray) {
      // statusList.add(Boolean.parseBoolean(status.trim()));
      // }

      // homeData.getGoals().add(new Goal(text, statusList));
      // System.out.println("goal: " + text + " | statuses: " + statusList);
      // }

      // plans 배열 파싱
      String plansSection = json.split("\"plans\":\\[")[1].split("\\]")[0];
      String[] plans = plansSection.split("\\},\\{");
      for (String plan : plans) {
        String text = extractValue(plan, "plan_text");
        String date = extractValue(plan, "goal_date");
        homeData.getPlans().add(new Plan(text, date));
      }

      // studies 배열 파싱
      String studiesSection = json.split("\"studies\":\\[")[1].split("\\]")[0];
      String[] studies = studiesSection.split("\\},\\{");
      for (String study : studies) {
        String text = extractValue(study, "study_text");
        String endDate = extractValue(study, "study_end_date");
        homeData.getStudies().add(new StudyItem(text, endDate));
      }

      // saying 파싱
      String sayingSection = json.split("\"saying\":\\{")[1].split("\\}")[0];
      String sayingText = extractValue(sayingSection, "saying_text");
      String sayingSpeaker = extractValue(sayingSection, "saying_speaker");
      homeData.setSaying(new Saying(sayingText, sayingSpeaker));

    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "JSON 데이터를 파싱하는 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }
  }

  private static String extractValue(String json, String key) {
    String[] pairs = json.replace("{", "").replace("}", "").replace("\"", "").split(",");
    for (String pair : pairs) {
      String[] keyValue = pair.split(":", 2); // 첫 번째 콜론만 분리
      if (keyValue[0].trim().equals(key)) {
        return keyValue[1].trim();
      }
    }
    return "";
  }

  // 데이터 클래스
  public static class HomeData {
    private String nickname;
    private List<Plan> plans = new ArrayList<>();
    private List<StudyItem> studies = new ArrayList<>();
    private Saying saying;

    public String getNickname() {
      return nickname;
    }

    public void setNickname(String nickname) {
      this.nickname = nickname;
    }

    public List<Plan> getPlans() {
      return plans;
    }

    public List<StudyItem> getStudies() {
      return studies;
    }

    public Saying getSaying() {
      return saying;
    }

    public void setSaying(Saying saying) {
      this.saying = saying;
    }
  }
}