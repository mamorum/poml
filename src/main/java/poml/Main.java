package poml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import poml.in.Poml;

public class Main {
  private static void exit(int i) { System.exit(i); }
  private static void ng(Opt opt) { opt.help(); exit(1); }

  // cmd "poml option"
  private static void option(Opt opt, String arg) throws Throwable {
    if ("-h".equals(arg)) opt.help();
    else if ("help".equals(arg)) opt.help();
    else if ("-v".equals(arg)) opt.version();
    else if ("version".equals(arg)) opt.version();
    else if ("mkdirs".equals(arg)) opt.mkdirs();
    else if ("init".equals(arg)) opt.init();
    else ng(opt); // not found
  }

  public static void main(String[] args) throws Throwable {
    if (args.length == 2) main(args[0], args[1]);
    else if (args.length == 1) option(new Opt(), args[0]);
    else ng(new Opt()); // invalid args
  }

  // cmd "poml pom.poml pom.xml"
  static void
    main(String pomlPath, String xmlPath)
  throws Throwable
  {
    long time = begin(pomlPath);
    try (BufferedReader in = open(pomlPath)) {
      String xml = Poml.parse(in).toXml();
      save(xml, xmlPath);
    }
    catch (Throwable e) {
      error(e, xmlPath);
      throw e;
    }
    success(xmlPath, time);
  }

  // cmd messages
  private static long begin(String pomlPath) {
    long time = System.currentTimeMillis();
    String msg = (new StringBuilder()
      ).append("[POML:INFO] Converting "
      ).append(pomlPath
    ).toString();
    System.out.println(msg);
    return time;
  }
  private static void success(String xmlPath, long time) {
    String uptime = String.valueOf(
      (System.currentTimeMillis() - time)
    );
    String msg = (new StringBuilder()
      ).append("[POML:INFO] Created "
      ).append(xmlPath).append(" @"
      ).append(uptime).append("ms"
    ).toString();
    System.out.println(msg);
  }
  private static void error(Throwable e, String xmlPath) {
    String msg = (new StringBuilder()
      ).append("[POML:ERROR] "
      ).append(e.getMessage()
      ).append(System.lineSeparator()
      ).append("[POML:ERROR] Could not create "
      ).append(xmlPath
    ).toString();
    System.out.println(msg);
  }

  // file operations
  static BufferedReader open(String path) throws IOException {
    return new BufferedReader(new InputStreamReader
      (new FileInputStream(path), "UTF-8")
    );
  }
  static void save(String txt, String path) throws IOException {
    try (
      BufferedWriter file =
        new BufferedWriter(new OutputStreamWriter(
          new FileOutputStream(path), "UTF-8"))
    ) {
      file.write(txt);
    }
  }
}