package poml.convert;

import poml.io.Poml;
import poml.io.Xml;

public class More {
  public static final String
    info="info", license="license", developer="developer";

  public static void all(Poml in, Xml out) {
    if (in.conf.has(info)) {out.nl(); info(in, out);}
    if (in.conf.has(license)) {
      out.nl();
      out.line("  <licenses>");
      license(in, out);
      out.line("  </licenses>");
    }
    if (in.conf.has(developer)) {
      out.nl();
      out.line("  <developers>");
      developer(in, out);
      out.line("  </developers>");
    }
  }
  //-> info=k>v, k>v ...
  public static void info(Poml in, Xml out) {;
    String[] kv = in.conf.csv(info);
    out.kv(Xml.sp2, kv);
  }

  //-> license=lic, lic ...
  public static void license(Poml in, Xml out) {
    for (String lic: in.conf.csv(license)) {
      out.line("    <license>");
      if (lic.equals("&apache2")) apache(out);
      else if (lic.equals("&mit")) mit(out);
      else lic(lic, in, out);
      out.line("    </license>");
    }
  }
  /// lic=k>v, k>v ...
  private static void lic(String lic, Poml in, Xml out) {
    String[] kv = in.conf.csv(lic);
    out.kv(Xml.sp6, kv);
  }
  private static void mit(Xml out) {
    out.line("      <name>MIT License</name>");
    out.line("      <url>https://opensource.org/licenses/MIT</url>");
  }
  private static void apache(Xml out) {
    out.line("      <name>The Apache License, Version 2.0</name>");
    out.line("      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>");
  }

  //-> developer=dev, dev ...
  public static void developer(Poml in, Xml out) {
    for (String dev: in.conf.csv(developer)) {
      dev(dev, in, out);
    }
  }
  /// dev=k>v, k>v ...
  private static void dev(String dev, Poml in, Xml out) {
    String[] kv = in.conf.csv(dev);
    out.line("    <developer>");
    out.kv(Xml.sp6, kv);
    out.line("    </developer>");
  }
}