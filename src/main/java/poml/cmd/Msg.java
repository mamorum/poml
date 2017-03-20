package poml.cmd;

import java.lang.management.ManagementFactory;

public class Msg {
  StringBuilder b;
  public Msg(StringBuilder b) { this.b = b; }
  public Msg add(Object msg) { b.append(msg); return this; }
  public Msg nl() { b.append(System.lineSeparator()); return this; }
  public void out() { System.out.print(b.toString()); }
  public void err() { System.err.print(b.toString()); }
  public static Msg init() { return new Msg(new StringBuilder()); }

  public static void start(String pomlPath) {
    Msg.init(
      ).add("[POML:INFO] Converting ").add(pomlPath).nl(
    ).out();
  }
  public static void error(Throwable e, String xmlPath) {
    Msg.init(
      ).add("[POML:ERROR] ").add(e.getMessage()).nl(
      ).add("[POML:ERROR] Could not create ").add(xmlPath).nl(
    ).err();
  }
  public static void end(String xmlPath) {
    Msg.init(
      ).add("[POML:INFO] Created "
      ).add(xmlPath).add(" @").add(
        ManagementFactory
          .getRuntimeMXBean().getUptime()
      ).add("ms").nl(
    ).out();
  }
}
