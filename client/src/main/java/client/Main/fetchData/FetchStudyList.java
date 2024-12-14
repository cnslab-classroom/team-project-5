package client.Main.fetchData;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import client.Main.model.GroupDetail;
import client.Main.model.MemberItem;
import client.Main.model.ReferenceLinkItem;
import client.Main.model.StudyItem;
import client.Main.model.ListItem;

public class FetchStudyList {

  // 스터디 그룹 리스트 데이터 가져오기
  public static List<ListItem> fetchStudyListData() {
    List<ListItem> studyItems = new ArrayList<>();

    try {
      URL url = new URL("http://localhost:8080/group/1/list"); // 서버 URL
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");

      int responseCode = conn.getResponseCode();
      System.out.println("서버 응답 코드: " + responseCode);

      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      StringBuilder response = new StringBuilder();
      String line;

      while ((line = br.readLine()) != null) {
        response.append(line);
      }
      br.close();

      System.out.println("서버 응답 데이터: " + response.toString());

      if (response.toString().contains("[]")) {
        System.out.println("서버 데이터가 비어있습니다.");
        return studyItems; // 빈 리스트 반환
      }

      parseStudyListJsonData(response.toString(), studyItems);

    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "데이터가 없습니다. 추가해 주세요.", "알림", JOptionPane.INFORMATION_MESSAGE);
    }

    return studyItems;
  }

  private static void parseStudyListJsonData(String json, List<ListItem> studyItems) {
    try {
      if (!json.contains("[") || !json.contains("]")) {
        System.out.println("JSON 데이터에 리스트가 없습니다.");
        return;
      }

      String studyGroupJson = json.substring(json.indexOf("[") + 1, json.lastIndexOf("]"));
      if (studyGroupJson.isEmpty()) {
        System.out.println("스터디 그룹 데이터가 비어 있습니다.");
        return;
      }

      String[] items = studyGroupJson.split("\\},\\{");

      for (String item : items) {
        item = decodeUnicode(item).replace("{", "").replace("}", "").replace("\"", "");

        String name = "", emoji = "";
        String[] pairs = item.split(",");

        for (String pair : pairs) {
          String[] keyValue = pair.split(":", 2); // 첫 번째 콜론만 분리
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

        if (!name.isEmpty() && !emoji.isEmpty()) {
          studyItems.add(new ListItem(emoji, name));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "JSON 파싱 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }
  }

  // 그룹 상세 정보 가져오기
  public static GroupDetail fetchGroupDetail(int groupId) {
    GroupDetail groupDetail = new GroupDetail();

    try {
      URL url = new URL("http://localhost:8080/group/" + groupId + "/detail");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");

      int responseCode = conn.getResponseCode();
      System.out.println("서버 응답 코드: " + responseCode);

      BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
      StringBuilder response = new StringBuilder();
      String line;

      while ((line = br.readLine()) != null) {
        response.append(line);
      }
      br.close();

      System.out.println("서버 응답 데이터: " + response.toString());

      parseGroupDetailJson(response.toString(), groupDetail);

    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "데이터가 없습니다. 추가해 주세요.", "알림", JOptionPane.INFORMATION_MESSAGE);
    }

    return groupDetail;
  }

  private static void parseGroupDetailJson(String json, GroupDetail groupDetail) {
    try {
      // "study" 배열 파싱
      if (json.contains("\"study\":")) {
        String[] studies = json.split("\"study\":\\[")[1].split("\\]")[0].split("\\},\\{");
        for (String study : studies) {
          String date = extractValue(study, "study_end_date");
          String text = extractValue(study, "study_text");
          groupDetail.studies.add(new StudyItem(date, text));
        }
      }

      // "members" 배열 파싱
      if (json.contains("\"members\":")) {
        String[] members = json.split("\"members\":\\[")[1].split("\\]")[0].split("\\},\\{");
        for (String member : members) {
          String emoji = extractValue(member, "emoji");
          String name = extractValue(member, "name");
          groupDetail.members.add(new MemberItem(emoji, name));
        }
      }

      // "reference_links" 배열 파싱
      if (json.contains("\"reference_links\":")) {
        String referenceLinksSection = json.split("\"reference_links\":\\[")[1].split("\\]")[0];
        String[] references = referenceLinksSection.split("\\},\\{");

        for (String reference : references) {
          String refName = extractValue(reference, "name");
          String url = extractValue(reference, "url");
          groupDetail.referenceLinks.add(new ReferenceLinkItem(refName, url));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "상세 데이터 파싱 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }
  }

  private static String extractValue(String json, String key) {
    String[] pairs = json.replace("{", "").replace("}", "").replace("\"", "").split(",");
    for (String pair : pairs) {
      String[] keyValue = pair.split(":", 2);
      if (keyValue[0].trim().equals(key)) {
        return keyValue[1].trim();
      }
    }
    return "";
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