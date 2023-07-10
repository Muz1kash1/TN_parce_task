package org.example.service;

import com.google.gson.Gson;
import org.example.IPInfoResponse;

public class JsonCreator {

  /**
   * Метод для создания Json
   *
   * @param ipAddress айпишник
   * @return строка json
   */
  public static String createJSON(String ipAddress) {
    IPInfoResponse response = new IPInfoResponse(ipAddress);
    return new Gson().toJson(response);
  }
}
