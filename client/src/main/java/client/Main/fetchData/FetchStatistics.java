package client.Main.fetchData;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import client.Main.model.Statistics;
import client.Main.model.DailyStatistics;

public class FetchStatistics {

  public static class StatisticsData {
    private List<Statistics> statics = new ArrayList<>();
    private List<DailyStatistics> dailyStatics = new ArrayList<>();

    public List<Statistics> getStatics() {
      return statics;
    }

    public List<DailyStatistics> getDailyStatics() {
      return dailyStatics;
    }
  }

  public static StatisticsData fetchStatisticsData() {
    StatisticsData statisticsData = new StatisticsData();

    try {
      URL url = new URL("http://localhost:8080/plan/1/statics"); // 서버 URL
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

      // JSON 데이터 파싱
      parseStatisticsJson(response.toString(), statisticsData);

    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "서버에서 데이터를 가져오지 못했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
    }

    return statisticsData;
  }

  private static void parseStatisticsJson(String json, StatisticsData statisticsData) {
    try {
      // statics 배열 파싱
      String staticsSection = json.split("\"statics\":\\[")[1].split("\\]")[0];
      String[] staticsArray = staticsSection.split("\\},\\{");
      for (String staticEntry : staticsArray) {
        String date = extractValue(staticEntry, "date");
        int achievement = Integer.parseInt(extractValue(staticEntry, "achievement"));
        statisticsData.getStatics().add(new Statistics(date, achievement));
      }

      // daily_statics 배열 파싱
      String dailySection = json.split("\"daily_statics\":\\[")[1].split("\\]")[0];
      String[] dailyArray = dailySection.split("\\},\\{");
      for (String dailyEntry : dailyArray) {
        String goalText = extractValue(dailyEntry, "goal_text");
        int dateAchievement = Integer.parseInt(extractValue(dailyEntry, "goal_date_achievement"));
        int wholeDate = Integer.parseInt(extractValue(dailyEntry, "goal_whole_date"));
        double goalPercent = Double.parseDouble(extractValue(dailyEntry, "goal_percent"));
        statisticsData.getDailyStatics().add(new DailyStatistics(goalText, dateAchievement, wholeDate, goalPercent));
      }

    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "JSON 데이터를 파싱하는 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
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
}