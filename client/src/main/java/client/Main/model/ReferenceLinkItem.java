package client.Main.model;

public class ReferenceLinkItem {
  private String name;
  private String url;

  public ReferenceLinkItem(String name, String url) {
    this.name = name;
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public String getUrl() {
    return url;
  }
}
