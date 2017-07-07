package poml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

import poml.io.Poml;

public class Main {
  public static void main(String[] args) throws Throwable {
    if (args.length == 2) convert(args[0], args[1]);
    else if (args.length == 1) option(args[0]);
    else ng(); // invalid args
  }

  // cmd "poml option"
  private static void option(String arg) throws Throwable {
    if ("-h".equals(arg)) Opt.help();
    else if ("help".equals(arg)) Opt.help();
    else if ("-v".equals(arg)) Opt.version();
    else if ("version".equals(arg)) Opt.version();
    else if ("mkdirs".equals(arg)) Opt.mkdirs();
    else if ("init".equals(arg)) Opt.init();
    else ng(); // not found
  }
  private static void ng() { Opt.help(); System.exit(1); }

  // cmd "poml pom.poml pom.xml"
  static void convert(
    String pomlPath, String xmlPath) throws Throwable
  {
    long time = start(pomlPath);
    try (BufferedReader in = open(pomlPath)) {
      String xml = Poml.of(in).toXml();
      save(xml, xmlPath);
    }
    catch (Throwable e) {
      err(e, xmlPath);
      throw e;
    }
    ok(xmlPath, time);
  }

  // messages
  private static final PrintStream o = System.out;
  private static long start(String pomlPath) {
    long time = System.currentTimeMillis();
    o.print("[INFO] Converting ");
    o.println(pomlPath);
    return time;
  }
  private static void ok(String xmlPath, long starTime) {
    o.print("[INFO] Created ");
    o.print(xmlPath);
    o.print(" @");
    o.print(String.valueOf(
      (System.currentTimeMillis() - starTime)
    ));  // poml uptime
    o.println("ms");
  }
  private static void err(Throwable e, String xmlPath) {
    o.print("[ERROR] ");
    o.println(e.getMessage());
    o.print("[ERROR] Could not create ");
    o.println(xmlPath);
  }

  // file operations
  private static final String utf8 = "UTF-8";
  private static BufferedReader open(String path) throws IOException {
    return new BufferedReader(new InputStreamReader
      (new FileInputStream(path), utf8)
    );
  }
  static void save(String txt, String path) throws IOException {
    try (BufferedWriter file =
      new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(path), utf8))
    ) {
      file.write(txt);
    }
  }
}