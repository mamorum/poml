package poml;

import poml.tools.Console;

public class Main {

  static Src src;
  static Dst dst;
  static String srcFile, dstFile;
  
  public static void main(String[] args) throws Throwable {
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
