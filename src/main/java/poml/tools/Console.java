package poml.tools;

import java.lang.management.ManagementFactory;

public class Console {
  public static void start(String srcFile) {
    System.out.print("[POML:INFO] Processing \"");
    System.out.print(srcFile);
    System.out.println("\"");
  }
  public static void error(Throwable e, String dstFile) {
    System.err.print("[POML:ERROR] ");
    System.err.println(e.getMessage());
    System.err.print("[POML:ERROR] Could not generate \"");
    System.err.print(dstFile);
    System.err.println("\"");
  }
  public static void end(String dstFile) {
    System.out.print("[POML:INFO] Genarated \"");
    System.out.print(dstFile);
    System.out.print("\" @");
    System.out.print(
        ManagementFactory
          .getRuntimeMXBean().getUptime());
    System.out.println("ms");
  }
}
