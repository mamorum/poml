package poml.convert;

import poml.io.Xml;

public class Prj {
  public static final String start="prj", end="/prj";
  public static void start(Xml out) {
    out.line(
      "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" " +
      "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
      "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 " +
      "http://maven.apache.org/xsd/maven-4.0.0.xsd\">");
    out.line("  <modelVersion>4.0.0</modelVersion>");
  }
  public static void end(Xml out) { out.txt("</project>"); }
}
