package org.example.service;

import com.google.gson.Gson;
import org.example.IPInfoResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class IPFetcher {
  private static final String API_URL = "https://api.ipify.org/?format=json";

  /**
   * Метод получающий с апишки айпишник с помощью jsoup
   *
   * @return айпишник
   * @throws IOException исключения на случай сбоя апи
   */
  public static String fetchIPAddress() throws IOException {
    Document document = Jsoup.connect(API_URL).ignoreContentType(true).get();
    String json = document.body().text();
    IPInfoResponse response = new Gson().fromJson(json, IPInfoResponse.class);
    return response.getIp();
  }
}
