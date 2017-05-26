package poml.convert;

import poml.Xml;

public class Prj {
  public static String name() { return "prj"; }
  public static void to(Xml out) {
    out.line(
      "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" " +
      "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
      "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 " +
      "http://maven.apache.org/xsd/maven-4.0.0.xsd\">");
    out.line("  <modelVersion>4.0.0</modelVersion>");
  }
}
