package client.Main.fetchData;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendPostGoal2 {
  public static void sendPutGoal2(Long goalId, boolean newStatus) {
    try {
      URL url = new URL("http://localhost:8080/plan/1/daily");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("PUT");
      conn.setRequestProperty("Content-Type", "application/json");
      conn.setDoOutput(true);

      // JSON 데이터 생성
      String jsonInputString = "{ \"goal_id\": " + goalId + ", \"plan_status\": " + newStatus + " }";

      try (OutputStream os = conn.getOutputStream()) {
        byte[] input = jsonInputString.getBytes("utf-8");
        os.write(input, 0, input.length);
      }

      int responseCode = conn.getResponseCode();
      System.out.println("서버 응답 코드: " + responseCode);

      if (responseCode == 200) {
        System.out.println("목표 상태가 성공적으로 업데이트되었습니다.");
      } else {
        System.out.println("목표 상태 업데이트 실패: " + responseCode);
      }

      conn.disconnect();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
