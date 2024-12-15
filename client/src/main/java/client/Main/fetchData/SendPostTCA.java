package client.Main.fetchData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

public class SendPostTCA {

    public static void sendPostTCA(String email, boolean agreed) {
        try {
            // 서버 URL
            URL url = new URL("http://localhost:8080/auth/agree");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST 요청 설정
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // JSON 데이터 생성
            String jsonInputString = String.format(
                "{ \"email\": \"%s\", \"agreed\": %b }",
                email, agreed
            );

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
                JOptionPane.showMessageDialog(null, "동의 완료");
            } else {
                JOptionPane.showMessageDialog(null, "예기치 않은 오류 발생: " + response.toString(), "오류", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "서버로 데이터를 전송하지 못했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}
