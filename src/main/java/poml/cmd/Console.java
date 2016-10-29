package poml.cmd;

import java.io.PrintStream;
import java.lang.management.ManagementFactory;

import poml.Main;

public class Console {
  private static final PrintStream out = System.out;
  private static final PrintStream err = System.err;
  private static String pomlPath, xmlPath;

  public static void version() {
    out.println(
      Main.class.getPackage()
        .getImplementationVersion()
    );
  }
  public static void help() {
    out.println();
    out.println("Usage: poml [option]");
    out.println();
    out.print("poml   ");
    out.println("\t   convert pom.poml to pom.xml");
    out.print("poml -h");
    out.println("\t   display this help");
    out.print("poml -v");
    out.println("\t   display version");
  }

  public static void start(String poml, String xml) {
    pomlPath = poml;
    xmlPath = xml;
    out.print("[POML:INFO] Processing \"");
    out.print(pomlPath);
    out.println("\"");
  }
  public static void error(Throwable e) {
    err.print("[POML:ERROR] ");
    err.println(e.getMessage());
    err.print("[POML:ERROR] Could not generate \"");
    err.print(xmlPath);
    err.println("\"");
  }
  public static void end() {
    out.print("[POML:INFO] Genarated \"");
    out.print(xmlPath);
    out.print("\" @");
    out.print(
      ManagementFactory
        .getRuntimeMXBean().getUptime()
    );
    out.println("ms");
  }
}