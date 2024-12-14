package client.Main.fetchData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

public class FetchStudyListAdd {

  public static void sendPostGoal(String studyName, String endDate, String emoji, int groupId) {
    try {
      // 서버 URL
      URL url = new URL("http://localhost:8080/group/" + groupId + "/add_study");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      // POST 요청 설정
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/json");
      conn.setDoOutput(true);

      // JSON 데이터 생성
      String jsonInputString = String.format(
          "{ \"study_name\": \"%s\", \"study_end_date\": \"%s\", \"study_emoji\": \"%s\" }",
          studyName, endDate, emoji);

      // 서버로 데이터 전송
      try (java.io.OutputStream os = conn.getOutputStream()) {
        byte[] input = jsonInputString.getBytes("utf-8");
        os.write(input, 0, input.length);
      }

      // 응답 코드 확인
      int responseCode = conn.getResponseCode();
      System.out.println("서버 응답 코드: " + responseCode);

      // 서버 응답 확인
      try (BufferedReader br = new BufferedReader(
          new InputStreamReader(conn.getInputStream(), "utf-8"))) {
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = br.readLine()) != null) {
          response.append(responseLine.trim());
        }
        System.out.println("서버 응답: " + response.toString());
      }

      // 성공 메시지
      JOptionPane.showMessageDialog(null, "목표가 추가되었습니다: " + studyName);

    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "서버로 데이터를 전송하지 못했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }
  }

}