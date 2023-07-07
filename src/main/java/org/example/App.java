package org.example;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Класс решающий задачу с получением и парсингом ip
 */
public class App {
  private static final String API_URL = "https://api.ipify.org/?format=json";
  private static final String FILE_NAME = "ip-info.txt";

  public static void main(
    String[] args) {
    try {
      boolean useFileOutput = args.length > 0 && args[0].equals("file");
      // проверка на то содержится ли в args file

      String ipAddress = fetchIPAddress();
      String json = createJSON(ipAddress);
      // если да то выведет в файл
      if (useFileOutput) {
        saveToFile(json);
      } else {
        System.out.println(json);
      }
    } catch (IOException e) {
      System.err.println("An error occurred: " + e.getMessage());
    }
  }

    /**
     * Метод получающий с апишки айпишник с помощью jsoup
     * @return айпишник
     * @throws IOException исключения на случай сбоя апи
     */
  private static String fetchIPAddress() throws IOException {
    Document document = Jsoup.connect(API_URL).ignoreContentType(true).get();
    String json = document.body().text();
    IPInfoResponse response = new Gson().fromJson(json, IPInfoResponse.class);
    return response.getIp();
  }

    /**
     * Метод для создания Json
     * @param ipAddress айпишник
     * @return строка json
     */
  private static String createJSON(String ipAddress) {
    IPInfoResponse response = new IPInfoResponse(ipAddress);
    return new Gson().toJson(response);
  }

    /**
     * Метод для сохранения айпишника в файл
     * @param json сохраняемый json
     * @throws IOException исключения на случай сбоя файла
     */
  private static void saveToFile(String json) throws IOException {
    Path filePath = Path.of(FILE_NAME);
    if (!Files.exists(filePath)) {
      Files.createFile(filePath);
    }
    Files.write(filePath, json.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    System.out.println("IP information saved to " + FILE_NAME);
  }
}
