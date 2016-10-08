package poml;

import poml.tools.Console;

public class Main {

  static Src src;
  static Dst dst;
  static String srcPath, dstPath;

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
    srcPath = args[0];
    dstPath = args[1];

    Console.start(srcPath);
    try {
      src = Src.open(srcPath);
      dst = Dst.openBuffer();
      src.toXml(dst);
      dst.save(dstPath);
    }
    catch (Throwable e) {
      Console.error(e, dstPath);
      throw e;
    }
    finally {
      close();
    }
    Console.end(dstPath);
  }

  private static void close() {
    try { if (dst != null) dst.closeBuffer(); }
    finally { if (src != null) src.close(); }
  }
}
