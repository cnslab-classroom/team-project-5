package client.Main.fetchData;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import client.Main.model.Profile;
import client.Main.model.Streak;

public class FetchProfile {

  public static ProfileData fetchProfileData() {
    ProfileData profileData = new ProfileData();

    try {
      // 서버 URL 설정
      URL url = new URL("http://localhost:8080/plan/1/profile");
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
      parseProfileJson(response.toString(), profileData);

    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "서버에서 데이터를 가져오지 못했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }

    return profileData;
  }

  private static void parseProfileJson(String json, ProfileData profileData) {
    try {
      // streak 데이터 파싱
      String streakSection = json.split("\"streak\":\\{")[1].split("\\}")[0];
      int streakDays = Integer.parseInt(extractValue(streakSection, "streakDays"));
      boolean todayGoalCompleted = Boolean.parseBoolean(extractValue(streakSection, "todayGoalCompleted"));

      Streak streak = new Streak(streakDays, todayGoalCompleted);
      profileData.setStreak(streak);

      // profile 데이터 파싱
      String profileSection = json.split("\"profile\":\\{")[1].split("\\}")[0];
      String name = extractValue(profileSection, "name");
      int status = Integer.parseInt(extractValue(profileSection, "status"));
      String icon = extractValue(profileSection, "icon");
      String bio = extractValue(profileSection, "bio");
      String affiliation = extractValue(profileSection, "affiliation");

      Profile profile = new Profile(name, status, icon, bio, affiliation);
      profileData.setProfile(profile);

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

  // 내부 데이터 클래스
  public static class ProfileData {
    private Streak streak;
    private Profile profile;

    public Streak getStreak() {
      return streak;
    }

    public void setStreak(Streak streak) {
      this.streak = streak;
    }

    public Profile getProfile() {
      return profile;
    }

    public void setProfile(Profile profile) {
      this.profile = profile;
    }
  }
}