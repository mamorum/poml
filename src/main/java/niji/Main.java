package niji;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  private static BufferedReader reader() throws IOException {
    return new BufferedReader(
        new InputStreamReader(
            Main.class.getResourceAsStream("/pom.nj"),
            "UTF-8"
        )
    );
  }
  
  static long start = System.currentTimeMillis();
  
  public static void main(String[] args) throws IOException {
    String xml = null;
    try (BufferedReader br = reader()) {
      xml = Processor.process(br);
    }
    finish(xml);
  }

  private static void finish(String xml) {
    // TODO output to pom.xml.
    System.out.println(xml);
    System.out.println(
        "Finished " +
        (System.currentTimeMillis() - start) +
        "msec."
    );
  }
}
