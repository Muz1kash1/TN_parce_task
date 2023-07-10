package org.example;

import org.example.strategy.ConsoleOutputStrategy;
import org.example.strategy.FileOutputStrategy;
import org.example.strategy.OutputStrategy;
import java.io.IOException;

/**
 * Класс решающий задачу с получением и парсингом ip
 */
public class App {

  public static void main(
    String[] args) {
    try {
      OutputStrategy outputStrategy;

      if (args.length > 0 && args[0].equals("file")) {
        outputStrategy = new FileOutputStrategy();
      } else {
        outputStrategy = new ConsoleOutputStrategy();
      }
      outputStrategy.output();
    } catch (IOException e) {
      System.err.println("An error occurred" + e.getMessage());
    }
  }
}
