package client.Main.fetchData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

public class SendPutGoal {

    public static void sendPutGoal(Long goal_id) {
        try {
            // 서버 URL 설정
            URL url = new URL("http://localhost:8080/plan/1/daily");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            // PUT 요청 설정
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            
            Boolean always_true = new Boolean(true);

            // JSON 데이터 생성
            String jsonInputString = String.format(
                "{\"goal_id\": %d, \"plan_status\": %b}",
                goal_id, always_true
            );

            // 요청 데이터 로그
            System.out.println("PUT JSON: " + jsonInputString);

            // 서버로 데이터 전송
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 응답 코드 확인
            int responseCode = conn.getResponseCode();
            System.out.println("서버 응답 코드: " + responseCode);

            // 서버 응답 확인
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            // 응답 처리
            if (responseCode == 200) {
                JOptionPane.showMessageDialog(null, "목표 체크 성공: " + response.toString());
            } else {
                JOptionPane.showMessageDialog(null, "목표 체크 실패: " + response.toString(), "오류", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "서버로 데이터를 전송하지 못했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}
