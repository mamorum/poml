package poml;

import poml.cmd.Msg;
import poml.cmd.Opt;

public class Main {
  private static void exit(int i) { System.exit(i); }
  private static void ng() { Opt.help(); exit(1); }

  // for cmd "poml option"
  private static void option(String opt) {
    if ("help".equals(opt)) Opt.help();
    else if ("version".equals(opt)) Opt.version();
    else if ("mkdirs".equals(opt)) Opt.mkdirs();
    else if ("init".equals(opt)) Opt.init();
    else ng(); // option not found
  }

  public static void main(String[] args) throws Throwable {
    if (args.length == 2) process(args[0], args[1]);
    else if (args.length == 1) option(args[0]);
    else ng(); // invalid args
  }

  // for cmd "poml pom.poml pom.xml"
  private static void process(
    String poml, String xml) throws Throwable
  {
    Msg.start(poml);
    try { Processor.start(poml, xml); }
    catch (Throwable e) {
      Msg.error(e, xml);
      throw e;
    }
    Msg.end(xml);
  }
}
