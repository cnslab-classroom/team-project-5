package client.Main.fetchData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

public class SendPostSchedule {

    public static void sendPostSchedule(String plan_text, String goal_date) {
        try {
            // 서버 URL
            URL url = new URL("http://localhost:8080/plan/1/schedule");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST 요청 설정
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // JSON 데이터 생성
            String jsonInputString = String.format(
                "{\"plan_text\": \"%s\", \"goal_date\": \"%s\"}",
                plan_text, goal_date);

            // 요청 데이터 로그
            System.out.println("POST JSON: " + jsonInputString);

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
                // 성공 메시지 파싱
                System.out.println("서버 응답: " + response.toString());
                JOptionPane.showMessageDialog(null, "스케줄 추가 완료: " + response.toString());
            } else {
                System.err.println("서버 응답: " + response.toString());
                JOptionPane.showMessageDialog(null, "오류 발생: " + response.toString(), "오류", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "서버로 데이터를 전송하지 못했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}
