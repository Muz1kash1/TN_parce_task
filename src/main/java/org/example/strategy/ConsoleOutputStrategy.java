package org.example.strategy;

import java.io.IOException;

import static org.example.service.IPFetcher.fetchIPAddress;
import static org.example.service.JsonCreator.createJSON;

public class ConsoleOutputStrategy implements OutputStrategy {
  @Override
  public void output() throws IOException {
    String ipAddress = fetchIPAddress();
    String json = createJSON(ipAddress);
    System.out.println(json);
  }
}
