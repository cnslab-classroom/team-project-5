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
      String icon = decodeUnicode(extractValue(profileSection, "icon")); // 유니코드 디코딩 적용
      String bio = extractValue(profileSection, "bio");
      String affiliation = extractValue(profileSection, "affiliation");

      Profile profile = new Profile(name, icon, bio, affiliation);
      profileData.setProfile(profile);

    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "JSON 데이터를 파싱하는 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }
  }

  private static String decodeUnicode(String input) {
    StringBuilder sb = new StringBuilder();
    String[] parts = input.split("\\\\u"); // "\\u"를 기준으로 문자열 분할
    sb.append(parts[0]);
    for (int i = 1; i < parts.length; i++) {
      String hex = parts[i].substring(0, 4); // 첫 4자리의 유니코드 값 추출
      sb.append((char) Integer.parseInt(hex, 16)); // 유니코드를 문자로 변환
      sb.append(parts[i].substring(4)); // 나머지 문자열 추가
    }
    return sb.toString();
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

  public static void sendPatchRequest(String name, String emoji, String intro, String affiliation) {
    try {
      // 서버 URL
      URL url = new URL("http://localhost:8080/plan/1/edit_profile");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      // PUT 요청 설정
      conn.setRequestMethod("PUT");
      conn.setRequestProperty("Content-Type", "application/json");
      conn.setDoOutput(true); // 출력 스트림 활성화

      // JSON 데이터 생성
      String jsonInputString = String.format(
          "{ \"name\": \"%s\", \"icon\": \"%s\", \"bio\": \"%s\", \"affiliation\": \"%s\" }",
          name, emoji, intro, affiliation);

      // 서버로 데이터 전송
      try (java.io.OutputStream os = conn.getOutputStream()) {
        byte[] input = jsonInputString.getBytes("utf-8");
        os.write(input, 0, input.length);
      }

      // 응답 코드 확인
      int responseCode = conn.getResponseCode();
      System.out.println("서버 응답 코드: " + responseCode);

      BufferedReader br;
      if (responseCode >= 200 && responseCode < 300) {
        br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
      } else {
        br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
      }

      // 응답 메시지 읽기
      StringBuilder response = new StringBuilder();
      String responseLine;
      while ((responseLine = br.readLine()) != null) {
        response.append(responseLine.trim());
      }
      br.close();

      System.out.println("서버 응답: " + response.toString());

      if (responseCode >= 200 && responseCode < 300) {
        JOptionPane.showMessageDialog(null, "프로필이 성공적으로 수정되었습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(null, "프로필 수정 실패: " + response.toString(), "오류", JOptionPane.ERROR_MESSAGE);
      }

    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "서버로 데이터를 전송하지 못했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }
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