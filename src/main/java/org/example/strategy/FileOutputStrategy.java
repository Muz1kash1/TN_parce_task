package org.example.strategy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.example.service.IPFetcher.fetchIPAddress;
import static org.example.service.JsonCreator.createJSON;


public class FileOutputStrategy implements OutputStrategy {
  private static final String FILE_NAME = "ip-info.txt";

  @Override
  public void output() throws IOException {
    String ipAddress = fetchIPAddress();
    String json = createJSON(ipAddress);
    Path filePath = Path.of(FILE_NAME);
    if (!Files.exists(filePath)) {
      Files.createFile(filePath);
    }
    Files.write(filePath, json.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    System.out.println("IP information saved to " + FILE_NAME);
  }
}
