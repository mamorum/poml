package poml;

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
    public static void version() {
      System.out.println(
        Main.class.getPackage()
          .getImplementationVersion()
      );
    }
    public static void help() {
      System.out.println();
      System.out.println("Usage: poml [option]");
      System.out.println();
      System.out.print("poml   ");
      System.out.println("\t   convert pom.poml to pom.xml");
      System.out.print("poml -h");
      System.out.println("\t   display this help");
      System.out.print("poml -v");
      System.out.println("\t   display version");
    }

    public static void start() {
      System.out.print("[POML:INFO] Processing \"");
      System.out.print(pomlPath);
      System.out.println("\"");
    }
    public static void error(Throwable e) {
      System.err.print("[POML:ERROR] ");
      System.err.println(e.getMessage());
      System.err.print("[POML:ERROR] Could not generate \"");
      System.err.print(xmlPath);
      System.err.println("\"");
    }
    public static void end() {
      System.out.print("[POML:INFO] Genarated \"");
      System.out.print(xmlPath);
      System.out.print("\" @");
      System.out.print(
          ManagementFactory
            .getRuntimeMXBean().getUptime()
      );
      System.out.println("ms");
    }
  }
}
