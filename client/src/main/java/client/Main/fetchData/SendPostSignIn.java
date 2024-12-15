package client.Main.fetchData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

public class SendPostSignIn {

    public static Integer sendPostSignIn(String signId, String password) {
        try {
            // 서버 URL 설정
            URL url = new URL("http://localhost:8080/auth/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // POST 요청 설정
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // JSON 데이터 생성
            String jsonInputString = String.format(
                "{ \"sign_id\": \"%s\", \"password\": \"%s\" }",
                signId, password);

            // 요청 데이터 로그 출력
            System.out.println("POST JSON: " + jsonInputString);

            // 서버로 데이터 전송
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 응답 코드 확인
            int responseCode = conn.getResponseCode();
            System.out.println("서버 응답 코드: " + responseCode);

            // 서버 응답 처리
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
                System.out.println("서버 응답: " + response.toString());

                // 서버 응답에서 member_id 추출
                int memberId = parseMemberId(response.toString());
                JOptionPane.showMessageDialog(null, "로그인 성공");
                return memberId;
            } else {
                JOptionPane.showMessageDialog(null, "로그인 실패: ", "오류", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "서버와의 연결에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    // 서버 응답에서 member_id 추출
    private static int parseMemberId(String response) {
        try {
            // 응답에서 "member_id" 키를 찾아 추출
            int memberIdStart = response.indexOf("\"member_id\":") + 12;
            int memberIdEnd = response.indexOf("}", memberIdStart);
            return Integer.parseInt(response.substring(memberIdStart, memberIdEnd).trim());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "응답에서 회원 ID를 추출하지 못했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
        return -1; // 에러 발생 시 기본값 반환
    }
}
