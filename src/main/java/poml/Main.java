package poml;

import poml.in.Poml;
import poml.out.Xml;

public class Main {
  private static void exit(int i) { System.exit(i); }
  private static void ng(Opt opt) { opt.help(); exit(1); }

  // cmd "poml option"
  private static void option(Opt opt, String arg) {
    if ("-h".equals(arg)) opt.help();
    else if ("help".equals(arg)) opt.help();
    else if ("-v".equals(arg)) opt.version();
    else if ("version".equals(arg)) opt.version();
    else if ("mkdirs".equals(arg)) opt.mkdirs();
    // TODO else if ("init".equals(opt)) Opt.init();
    else ng(opt); // option not found
  }

  public static void main(String[] args) throws Throwable {
    if (args.length == 2) main(args[0], args[1]);
    else if (args.length == 1) option(new Opt(), args[0]);
    else ng(new Opt()); // invalid args
  }

  // cmd "poml pom.poml pom.xml"
  private static void
    main(String pomlPath, String xmlPath)
  throws Throwable
  {
    long time = begin(pomlPath);
    Poml poml = new Poml();
    Xml xml = new Xml();
    try {
      poml.open(pomlPath);
      poml.to(xml);
      xml.save(xmlPath);
      success(xmlPath, time);
    }
    catch (Throwable e) {
      error(e, xmlPath);
      throw e;
    }
    finally { poml.close(); }
  }

  // cmd messages
  public static long begin(String pomlPath) {
    long time = System.currentTimeMillis();
    String msg = (new StringBuilder()
      ).append("[POML:INFO] Converting "
      ).append(pomlPath
    ).toString();
    System.out.println(msg);
    return time;
  }
  public static void success(String xmlPath, long time) {
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
  public static void error(Throwable e, String xmlPath) {
    String msg = (new StringBuilder()
      ).append("[POML:ERROR] "
      ).append(e.getMessage()
      ).append(System.lineSeparator()
      ).append("[POML:ERROR] Could not create "
      ).append(xmlPath
    ).toString();
    System.out.println(msg);
  }
}