package poml;

import java.io.IOException;
import java.lang.management.ManagementFactory;

public class Main {

  static Src src;
  static Dst dst;
  
  public static void main(String[] args) throws IOException {
    Console.start();
    try {
      src = Src.open(args[0]).loadProperties();
      dst = Dst.open(args[1]).load(src);
    } finally {
      close();
    }
    Console.end();
  }

  private static void close() throws IOException {
    try { if (src != null) src.close(); }
    finally { if (dst != null) dst.close(); }
  }

  private static class Console {
    static void start() {
      System.out.println("Start to process poml ...");
    }
    static void end() {
      System.out.print("pom.xml generated @");
      System.out.print(
          ManagementFactory
            .getRuntimeMXBean().getUptime());
      System.out.println("ms.");
    }
  }
}
