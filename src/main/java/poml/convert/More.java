package poml.convert;

import java.util.Map;

import poml.io.Poml;
import poml.io.Xml;

public class More {
  public static final String
    info="info", license="license", dev="developer";

  public static void all(Poml in, Xml out) {
    if (in.conf.has(info)) {out.nl(); More.info(in, out);}
    if (in.conf.has(license)) {
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
  //-> info
  private static final String[] infoTags = {
    "name", "description", "url", "inceptionYear"
  };
  public static void info(Poml in, Xml out) {
    Map<String, String> kv = in.conf.map(info);
    out.tags(Xml.sp2, infoTags, kv);
  }

  //-> license
  public static void license(Poml in, Xml out) {
    String[] lics = in.conf.vals(license);
    for (String lic: lics) {
      out.line("    <license>");
      if ("&apache2".equals(lic)) apache(out);
      else if ("&mit".equals(lic)) mit(out);
      else $license(lic, in, out);
      out.line("    </license>");
    }
  }
  private static void apache(Xml out) {
    out.line("      <name>The Apache License, Version 2.0</name>");
    out.line("      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>");
  }
  private static void mit(Xml out) {
    out.line("      <name>MIT License</name>");
    out.line("      <url>https://opensource.org/licenses/MIT</url>");
  }
  private static void $license(String lic, Poml in, Xml out) {
    Map<String, String> kv = in.conf.map(lic);
    out.tags(Xml.sp6, licenseTags, kv);
  }
  private static final String[] licenseTags = {
    "name", "url", "distribution", "comments"
  };

  //-> developer
  private static final String[]
    devTags = { "id", "name","email", "url"};
  public static void developer(Poml in, Xml out) {
    String[] devs = in.conf.vals(dev);
    for (String $dev: devs) {
      Map<String, String> kv = in.conf.map($dev);
      out.line("    <developer>");
      out.tags(Xml.sp6, devTags, kv);
      out.line("    </developer>");
    }
  }
}