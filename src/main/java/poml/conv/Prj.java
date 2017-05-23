package poml.conv;

import poml.in.Poml;
import poml.out.Xml;

public class Prj implements Converter {
  @Override public String name() { return "prj"; }
  @Override public void convert(Poml in, Xml out) {
    out.line(
      "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" " +
      "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
      "xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 " +
      "http://maven.apache.org/xsd/maven-4.0.0.xsd\">");
    out.line("  <modelVersion>4.0.0</modelVersion>");
  }
}
