package poml.convert;

import poml.io.Poml;
import poml.io.Xml;

public class More {
  public static final String
    info="info", lic="license", dev="developer";

  public static void all(Poml in, Xml out) {
    if (in.conf.has(info)) {out.nl(); More.info(in, out);}
    if (in.conf.has(lic)) {
      out.nl();
      out.line("  <licenses>");
      More.license(in, out);
      out.line("  </licenses>");
    }
    if (in.conf.has(dev)) {
      out.nl();
      out.line("  <developers>");
      More.developer(in, out);
      out.line("  </developers>");
    }
  }
  //-> info=k>v, k>v ...
  public static void info(Poml in, Xml out) {;
    out.kvs(Xml.sp2, in.conf.csv(info), info);
  }

  //-> license=v, v ...
  public static void license(Poml in, Xml out) {
    for (String v: in.conf.csv(lic)) {
      out.line("    <license>");
      if ("&apache2".equals(v)) apache(out);
      else if ("&mit".equals(v)) mit(out);
      else $lic(v, in, out);
      out.line("    </license>");
    }
  }
  //// $key=k>v, k>v ...
  private static void $lic(String $key, Poml in, Xml out) {
    String[] kv = in.conf.csv($key);
    out.kvs(Xml.sp6, kv, $key);
  }
  private static void mit(Xml out) {
    out.line("      <name>MIT License</name>");
    out.line("      <url>https://opensource.org/licenses/MIT</url>");
  }
  private static void apache(Xml out) {
    out.line("      <name>The Apache License, Version 2.0</name>");
    out.line("      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>");
  }

  //-> developer=v, v ...
  public static void developer(Poml in, Xml out) {
    for (String v: in.conf.csv(dev)) $dev(v, in, out);
  }
  //// $key=k>v, k>v ...
  private static void $dev(String $key, Poml in, Xml out) {
    out.line("    <developer>");
    String[] kv = in.conf.csv($key);
    out.kvs(Xml.sp6, kv, $key);
    out.line("    </developer>");
  }
}