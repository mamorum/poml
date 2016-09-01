package niji.util;

public class Str {

  static final String nl = System.lineSeparator();
  static final char sp = ' ';
  
  public static void sp(int width, StringBuilder dst) {
    for (int i = 0; i < width; i++) dst.append(sp);
  }
  
  public static void nl(StringBuilder dst) {
    dst.append(nl);
  }
  
  public static void ln(
    String line, StringBuilder dst
  ) {
    dst.append(line);
    nl(dst);
  }

  public static void ln(
    int indent, String val, StringBuilder dst
  ) {
    sp(indent, dst);
    ln(val, dst);
  }

}
