package poml;

import poml.io.Poml;
import poml.io.Txt;
import poml.io.Xml;

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
  static String info = "[POML:INFO] ";
  public static long begin(String pomlPath) {
    long time = System.currentTimeMillis();
    System.out.print(Txt.init(
      ).add(info).add("Converting ").add(pomlPath).nl(
    ).toString());
    return time;
  }
  public static void success(String xmlPath, long time) {
    String uptime = String.valueOf(
      (System.currentTimeMillis() - time)
    );
    System.out.print(Txt.init(
      ).add(info).add("Created ").add(xmlPath
      ).add(" @").add(uptime).add("ms").nl(
    ).toString());
  }
  public static void error(Throwable e, String xmlPath) {
    String err = "[POML:ERROR] ";
    System.out.print(Txt.init(
      ).add(err).add(e.getMessage()).nl(
      ).add(err).add("Could not create ").add(xmlPath).nl(
    ).toString());
  }
}