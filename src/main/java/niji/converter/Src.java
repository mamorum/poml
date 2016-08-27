package niji.converter;

import java.util.Properties;

public class Src {
  public Properties p;
  public int indent;
  
  private Src(Properties p, int indent) {
    this.p = p; this.indent = indent;
  }
  public static Src of(Properties p, int indent) {
    return new Src(p, indent);
  }
}
