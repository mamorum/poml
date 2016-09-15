package poml;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

  static Src src;
  static Dst dst;
  
  public static void main(String[] args) throws IOException {
    try {
      // TODO Get file path from args ?
      src = Src.open("_pom.nj").loadProperties();
      dst = Dst.open("_pom.xml").from(src);
    }
    finally {
      close();
      inform();
      debug();
    }
  }

  private static void close() throws IOException {
    try { if (src != null) src.close(); }
    finally { if (dst != null) dst.close(); }
  }

  private static void inform() {
    System.out.print("pom.xml generated @");
    System.out.print(
        ManagementFactory
          .getRuntimeMXBean().getUptime());
    System.out.println("ms.");
  }
  
  private static void debug() throws IOException {
    Files.lines(Paths.get("_pom.xml")).forEach(s -> {
      System.out.println(s);
    });
  }
}
