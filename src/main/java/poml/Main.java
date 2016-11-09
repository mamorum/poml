package poml;

import poml.cmd.Console;
import poml.cmd.Init;

public class Main {

  private static void exit(int i) { System.exit(i); }

  private static void preProcess(String[] args) {
    if (args.length == 2) return; // -> convert
    if (args.length == 1) { // -> option
      if ("-h".equals(args[0])) { Console.help(); exit(0);}
      if ("-v".equals(args[0])) { Console.version(); exit(0); }
      if ("-i".equals(args[0])) { Init.project(); exit(0); }
    }
    // -> mistake args.
    Console.help(); exit(1);
  }

  public static void main(String[] args) throws Throwable {
    preProcess(args);  // -> cmd "poml -option" exits.
    // -> cmd "poml pom.poml pom.xml" starts.
    Console.start(args[0], args[1]);
    try { Processor.start(args[0], args[1]); }
    catch (Throwable e) {
      Console.error(e);
      throw e;
    }
    Console.end();
  }
}
