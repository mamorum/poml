package poml;

import poml.tools.Console;

public class Main {

  static Src src;
  static Dst dst;
  static String srcFile, dstFile;

  private static void check(String[] args) {
    if (args.length == 2) return; // -> convert
    if (args.length == 1) { // -> option
      if ("-h".equals(args[0])) { Console.help(); System.exit(0);}
      if ("-v".equals(args[0])) { Console.version(); System.exit(0); }
    }
    Console.help(); System.exit(1);
  }

  public static void main(String[] args) throws Throwable {
    check(args);
    srcFile = args[0];
    dstFile = args[1];

    Console.start(srcFile);
    try {
      src = Src.open(srcFile).loadProperties();
      dst = Dst.open().load(src).save(dstFile);
    }
    catch (Throwable e) {
      Console.error(e, dstFile);
      throw e;
    }
    finally {
      close();
    }
    Console.end(dstFile);
  }

  private static void close() {
    try { if (dst != null) dst.close(); }
    finally { if (src != null) src.close(); }
  }
}
