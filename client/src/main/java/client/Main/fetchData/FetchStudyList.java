package client.Main.fetchData;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import client.Main.model.StudyItem;

public class FetchStudyList {

  public static List<StudyItem> fetchData() {
    List<StudyItem> studyItems = new ArrayList<>();

    try {
      URL url = new URL("http://localhost:8080/group/1/list"); // 서버 URL
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");

      int responseCode = conn.getResponseCode();
      System.out.println("서버 응답 코드: " + responseCode); // HTTP 상태 코드 출력

      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      StringBuilder response = new StringBuilder();
      String line;

      while ((line = br.readLine()) != null) {
        response.append(line);
      }
      br.close();

      // 서버에서 받은 JSON 데이터를 로그로 출력
      System.out.println("서버 응답 데이터: " + response.toString());

      // JSON 데이터 파싱
      parseJsonData(response.toString(), studyItems);

    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "서버에서 데이터를 가져오지 못했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }

    return studyItems;
  }

  private static void parseJsonData(String json, List<StudyItem> studyItems) {
    try {
      // JSON 전체에서 "study_group" 배열만 추출
      String studyGroupJson = json.substring(json.indexOf("[") + 1, json.lastIndexOf("]"));

      // 배열을 개별 객체로 나눔
      String[] items = studyGroupJson.split("\\},\\{");

      for (String item : items) {
        item = decodeUnicode(item); // 유니코드 변환 추가
        item = item.replace("{", "").replace("}", "").replace("\"", "");

        String name = "", emoji = "";
        String[] pairs = item.split(",");

        for (String pair : pairs) {
          String[] keyValue = pair.split(":");
          if (keyValue.length == 2) {
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            if (key.equals("study_group_name")) {
              name = value;
            } else if (key.equals("study_emoji")) {
              emoji = value;
            }
          }
        }

        // StudyItem 객체 생성 및 리스트에 추가
        if (!name.isEmpty() && !emoji.isEmpty()) {
          studyItems.add(new StudyItem(emoji, name));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "JSON 데이터를 파싱하는 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }
  }

  private static String decodeUnicode(String input) {
    StringBuilder sb = new StringBuilder();
    String[] parts = input.split("\\\\u");
    sb.append(parts[0]);
    for (int i = 1; i < parts.length; i++) {
      String hex = parts[i].substring(0, 4);
      sb.append((char) Integer.parseInt(hex, 16));
      sb.append(parts[i].substring(4));
    }
    return sb.toString();
  }
}