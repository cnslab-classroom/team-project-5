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

  public static void sendPostMember(String invite_email, int groupId) {
    try {
      // 서버 URL
      URL url = new URL("http://localhost:8080/group/" + groupId + "/invite");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      // POST 요청 설정
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/json");
      conn.setDoOutput(true);

      // JSON 데이터 생성
      String jsonInputString = String.format(
          "{ \"invite_email\": \"%s\" }", invite_email);

      // 서버로 데이터 전송
      try (java.io.OutputStream os = conn.getOutputStream()) {
        byte[] input = jsonInputString.getBytes("utf-8");
        os.write(input, 0, input.length);
      }

      // 응답 코드 확인
      int responseCode = conn.getResponseCode();
      System.out.println("서버 응답 코드: " + responseCode);

      // 서버 응답 확인
      BufferedReader br;
      if (responseCode >= 200 && responseCode < 300) {
        br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
      } else {
        br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
      }

      StringBuilder response = new StringBuilder();
      String responseLine;
      while ((responseLine = br.readLine()) != null) {
        response.append(responseLine.trim());
      }
      br.close();

      System.out.println("서버 응답: " + response.toString());

      // JSON에서 message 필드 추출
      String message = extractJsonField(response.toString(), "message");

      // 성공 메시지
      if (responseCode >= 200 && responseCode < 300) {
        JOptionPane.showMessageDialog(null, "멤버가 추가되었습니다: " + invite_email);
      } else {
        JOptionPane.showMessageDialog(null, "오류 응답: " + message, "서버 오류", JOptionPane.ERROR_MESSAGE);
      }

    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "서버로 데이터를 전송하지 못했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }
  }

  // JSON 필드 추출 메서드
  private static String extractJsonField(String json, String key) {
    try {
      // 키 값을 찾음
      String searchKey = "\"" + key + "\":";
      int startIndex = json.indexOf(searchKey);
      if (startIndex == -1) {
        return "해당 키를 찾을 수 없습니다.";
      }

      // 값의 시작 지점 설정
      startIndex += searchKey.length();

      // 값의 끝 지점 찾기
      char quoteChar = json.charAt(startIndex) == '"' ? '"' : ' '; // 값이 문자열인지 확인
      int endIndex = quoteChar == '"'
          ? json.indexOf(quoteChar, startIndex + 1)
          : json.indexOf(",", startIndex);

      if (endIndex == -1) {
        endIndex = json.indexOf("}", startIndex);
      }

      // 값 추출
      return json.substring(startIndex, endIndex).replace("\"", "").trim();
    } catch (Exception e) {
      e.printStackTrace();
      return "JSON 파싱 오류";
    }
  }

  public static void sendPostReference(String reference_name, String reference_url, int groupId) {
    try {
      // 서버 URL
      URL url = new URL("http://localhost:8080/group/" + groupId + "/add_reference");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      // POST 요청 설정
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/json");
      conn.setDoOutput(true);

      // JSON 데이터 생성
      String jsonInputString = String.format(
          "{ \"reference_name\": \"%s\", \"reference_url\": \"%s\" }", reference_name, reference_url);

      System.out.println("json:"+jsonInputString);
      // 서버로 데이터 전송
      try (java.io.OutputStream os = conn.getOutputStream()) {
        byte[] input = jsonInputString.getBytes("utf-8");
        os.write(input, 0, input.length);
      }

      // 응답 코드 확인
      int responseCode = conn.getResponseCode();
      System.out.println("서버 응답 코드: " + responseCode);

      // 서버 응답 확인
      BufferedReader br;
      if (responseCode >= 200 && responseCode < 300) {
        br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
      } else {
        br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
      }

      StringBuilder response = new StringBuilder();
      String responseLine;
      while ((responseLine = br.readLine()) != null) {
        response.append(responseLine.trim());
      }
      br.close();

      System.out.println("서버 응답: " + response.toString());

      // JSON에서 message 필드 추출
      String message = extractJsonField(response.toString(), "message");

      // 성공 메시지
      if (responseCode >= 200 && responseCode < 300) {
        JOptionPane.showMessageDialog(null, "레퍼런스가 추가되었습니다: " + reference_name);
      } else {
        JOptionPane.showMessageDialog(null, "오류 응답: " + message, "서버 오류", JOptionPane.ERROR_MESSAGE);
      }

    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "서버로 데이터를 전송하지 못했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }
  }
}