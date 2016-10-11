package poml;

import java.io.PrintStream;
import java.lang.management.ManagementFactory;

public class Main {

  static Poml poml;
  static Xml xml;
  static String pomlPath, xmlPath;

  private static void check(String[] args) {
    if (args.length == 2) return; // -> convert
    if (args.length == 1) { // -> option
      if ("-h".equals(args[0])) { Console.help(); System.exit(0);}
      if ("-v".equals(args[0])) { Console.version(); System.exit(0); }
    }
    Console.help(); System.exit(1);
  }

  // Convert pom.poml to pom.xml.
  public static void main(String[] args) throws Throwable {
    check(args);
    pomlPath = args[0];
    xmlPath = args[1];

    Console.start();
    try {
      poml = Poml.open(pomlPath);
      xml = Xml.openBuffer();
      poml.loadConfig();
      poml.layoutTo(xml);
      xml.save(xmlPath);
    }
    catch (Throwable e) {
      Console.error(e);
      throw e;
    }
    finally { close(); }
    Console.end();
  }

  private static void close() {
    try { if (xml != null) xml.closeBuffer(); }
    finally { if (poml != null) poml.close(); }
  }
  
  public static class Console {
    static PrintStream out = System.out;
    static PrintStream err = System.err;
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

    public static void start() {
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
}
